package com.vaya.controllers;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaya.domain.Post;
import com.vaya.services.EtcService;
import com.vaya.services.MeetingService;
import com.vaya.services.MemberService;
import com.vaya.services.PostService;
import com.vaya.services.RetreatService;
import com.vaya.services.TeamService;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	private PostService postService;
	private MemberService memberService;
	private TeamService teamService;
	private EtcService etcService;
	private MeetingService meetingService;
	private RetreatService retreatService;
	

	@Autowired
	public PostController(PostService postService, MemberService memberService, TeamService teamService,
			EtcService etcService, MeetingService meetingService, RetreatService retreatService) {
		super();
		this.postService = postService;
		this.memberService = memberService;
		this.teamService = teamService;
		this.etcService = etcService;
		this.meetingService = meetingService;
		this.retreatService = retreatService;
	}

	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list")
	public String postList(Principal principal, Model model) {
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post.
		 */
		model.addAttribute("owner",principal.getName());
		model.addAttribute("posts",postService.list());
		return "/posts/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/byMember/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("posts", postService.listByMember(id));
		return "posts/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/view/{id}")
	public String view(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("post",postService.get(id));
		return "posts/view";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("post",postService.get(id));
		model.addAttribute("members", memberService.list());
		model.addAttribute("teams", teamService.teamList());
		model.addAttribute("etcs", etcService.etcList());
		model.addAttribute("meetings", meetingService.meetingList());
		model.addAttribute("retreats", retreatService.retreatList());
		return "posts/postForm";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		postService.delete(id);
		redirectAttrs.addFlashAttribute("message", "Post was deleted!");
		return "redirect:/posts/list";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/create")
	public String create(Principal principal, Model model) {
		Post post = new Post();
		post.setApproval("pending");
		model.addAttribute("post",post);		 
		model.addAttribute("members", memberService.list());
		model.addAttribute("teams", teamService.teamList());
		model.addAttribute("etcs", etcService.etcList());
		model.addAttribute("meetings", meetingService.meetingList());
		model.addAttribute("retreats", retreatService.retreatList());
		return "/posts/postForm";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid Post post, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("members", memberService.list());
			return "/posts/postForm";
		} else {
			Post savedPost = postService.save(post);
			return "redirect:/posts/view/" + savedPost.getPostId();
		}

	}
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
