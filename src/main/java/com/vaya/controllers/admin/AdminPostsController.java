package com.vaya.controllers.admin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaya.domain.Member;
import com.vaya.domain.Post;
import com.vaya.excel.FileUploadValidator;
import com.vaya.services.EtcService;
import com.vaya.services.MeetingService;
import com.vaya.services.MemberService;
import com.vaya.services.PostService;
import com.vaya.services.RetreatService;
import com.vaya.services.TeamService;

@Controller
@Secured({ "ROLE_ADMIN" })
public class AdminPostsController {
	private PostService postService;
	private MemberService memberService;
	private TeamService teamService;
	private EtcService etcService;
	private MeetingService meetingService;
	private RetreatService retreatService;
	private FileUploadValidator fileValidator;

	@Autowired
	public AdminPostsController(PostService postService, MemberService memberService, TeamService teamService,
			EtcService etcService, MeetingService meetingService, RetreatService retreatService,
			FileUploadValidator fileValidator) {
		this.postService = postService;
		this.memberService = memberService;
		this.teamService = teamService;
		this.etcService = etcService;
		this.meetingService = meetingService;
		this.retreatService = retreatService;
		this.fileValidator = fileValidator;
	}

	@RequestMapping("/admin/posts")
	public String list(@ModelAttribute("member") Member member, Model model) {
		model.addAttribute("posts", postService.list());
		model.addAttribute("memberId", member.getMemberId());
		return "admin/posts/list";
	}

	@RequestMapping("/admin/post/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("post", postService.get(id));
		return "admin/posts/view";
	}

	/*
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {

		byte[] imageContent = postService.get(id).getFileData();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}

	// create | save

	@RequestMapping("/admin/post/create/")
	public String create(Principal principal, Model model) {
		Post post = new Post();
		post.setApproval("pending");
		model.addAttribute("post",post);
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post.
		 * model.addAttribute("member",memberService.getMember(principal.getName
		 * ()));
		 */
		model.addAttribute("members", memberService.list());
		model.addAttribute("teams", teamService.teamList());
		model.addAttribute("etcs", etcService.etcList());
		model.addAttribute("meetings", meetingService.meetingList());
		model.addAttribute("retreats", retreatService.retreatList());
		return "admin/posts/postForm";
	}

	@RequestMapping(value = "/admin/post/save", method = RequestMethod.POST)
	public String save(@Valid Post post, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("members", memberService.list());
			return "admin/posts/postForm";
		} else {
			Post savedPost = postService.save(post);
			return "redirect:/admin/post/" + savedPost.getPostId();
		}

	}

	@RequestMapping("/admin/post/edit/{id}")
	public String edit(@PathVariable Long id, Principal principal, Model model) {
		model.addAttribute("post", postService.get(id));
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post. model.addAttribute("member",
		 * memberService.getMember(principal.getName()));
		 */
		model.addAttribute("members", memberService.list());
		model.addAttribute("teams", teamService.teamList());
		model.addAttribute("etcs", etcService.etcList());
		model.addAttribute("meetings", meetingService.meetingList());
		model.addAttribute("retreats", retreatService.retreatList());
		return "admin/posts/postForm";
	}

	@RequestMapping("/admin/post/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		postService.delete(id);
		redirectAttrs.addFlashAttribute("message", "Post was deleted!");
		return "redirect:/admin/posts";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
