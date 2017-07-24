package com.vaya.postAccounting.controllers.admin;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.ServletException;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaya.etc.services.impl.EtcServiceImpl;
import com.vaya.meeting.services.impl.MeetingServiceImpl;
import com.vaya.member.domain.Member;
import com.vaya.member.services.MemberService;
import com.vaya.postAccounting.domain.PostAccounting;
import com.vaya.postAccounting.services.impl.PostAccountingServiceImpl;
import com.vaya.retreat.services.impl.RetreatServiceImpl;
import com.vaya.team.services.impl.TeamServiceImpl;

@Controller
@Secured({ "ROLE_ADMIN" })
public class AdminPostAccountingController {
	private PostAccountingServiceImpl postAccountingServiceImpl;
	private MemberService memberService;
	private TeamServiceImpl teamServiceImpl;
	private EtcServiceImpl etcServiceImpl;
	private MeetingServiceImpl meetingServiceImpl;
	private RetreatServiceImpl retreatServiceImpl;

	@Autowired
	public AdminPostAccountingController(PostAccountingServiceImpl postAccountingServiceImpl, MemberService memberService, TeamServiceImpl teamServiceImpl,
			EtcServiceImpl etcServiceImpl, MeetingServiceImpl meetingServiceImpl, RetreatServiceImpl retreatServiceImpl) {
		this.postAccountingServiceImpl = postAccountingServiceImpl;
		this.memberService = memberService;
		this.teamServiceImpl = teamServiceImpl;
		this.etcServiceImpl = etcServiceImpl;
		this.meetingServiceImpl = meetingServiceImpl;
		this.retreatServiceImpl = retreatServiceImpl;
	}

	@RequestMapping("/admin/postaccountings")
	public String list(@ModelAttribute("member") Member member, Model model) {
		model.addAttribute("posts", postAccountingServiceImpl.list());
		model.addAttribute("memberId", member.getMemberId());
		return "admin/postaccountings/list";
	}

	@RequestMapping("/admin/postaccountings/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("post", postAccountingServiceImpl.get(id));
		return "admin/postaccountings/view";
	}

	/*
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value = "/postaccountings/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
		byte[] imageContent = postAccountingServiceImpl.get(id).getFileData();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}

	// create | save

	@RequestMapping("/admin/postaccountings/create/")
	public String create(Principal principal, Model model,@PageableDefault(value=10) Pageable pageable) {
		PostAccounting post = new PostAccounting();
		post.setApproval("pending");
		model.addAttribute("post",post);
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post.
		 * model.addAttribute("member",memberService.getMember(principal.getName
		 * ()));
		 */
		model.addAttribute("members", memberService.list(pageable));
		model.addAttribute("teams", teamServiceImpl.teamList(pageable));
		model.addAttribute("etcs", etcServiceImpl.etcList());
		model.addAttribute("meetings", meetingServiceImpl.meetingList());
		model.addAttribute("retreats", retreatServiceImpl.retreatList());
		return "admin/postaccountings/postForm";
	}

	@RequestMapping(value = "/admin/postaccountings/save", method = RequestMethod.POST)
	public String save(@Valid PostAccounting post, BindingResult bindingResult, Model model,@PageableDefault(value=10) Pageable pageable) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("members", memberService.list(pageable));
			return "admin/postaccountings/postForm";
		} else {
			PostAccounting savedPost = postAccountingServiceImpl.save(post);
			return "redirect:/admin/postaccountings/" + savedPost.getPostId();
		}

	}

	@RequestMapping("/admin/postaccountings/edit/{id}")
	public String edit(@PathVariable Long id, Principal principal, Model model,@PageableDefault(value=10) Pageable pageable) {
		model.addAttribute("post", postAccountingServiceImpl.get(id));
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post. model.addAttribute("member",
		 * memberService.getMember(principal.getName()));
		 */
		model.addAttribute("members", memberService.list(pageable));
		model.addAttribute("teams", teamServiceImpl.teamList(pageable));
		model.addAttribute("etcs", etcServiceImpl.etcList());
		model.addAttribute("meetings", meetingServiceImpl.meetingList());
		model.addAttribute("retreats", retreatServiceImpl.retreatList());
		return "admin/postaccountings/postForm";
	}

	@RequestMapping("/admin/postaccountings/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		postAccountingServiceImpl.delete(id);
		redirectAttrs.addFlashAttribute("message", "Post was deleted!");
		return "redirect:/admin/postaccountings";
	}

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
