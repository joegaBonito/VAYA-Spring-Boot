package com.vaya.postTeam.controllers;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.vaya.member.services.MemberService;
import com.vaya.postSmallGroup.domain.PostSmallGroup;
import com.vaya.postTeam.domain.PostTeam;
import com.vaya.postTeam.services.impl.PostTeamServiceImpl;

@Controller
@RequestMapping("/postteams")
public class PostTeamController {
	
	private MemberService memberService;
	private PostTeamServiceImpl postTeamServiceImpl;
	
	@Autowired
	public PostTeamController(PostTeamServiceImpl postTeamServiceImpl, MemberService memberService) {
		this.postTeamServiceImpl = postTeamServiceImpl; 
		this.memberService = memberService;
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list") 
	public String postList(Principal principal, Model model) {
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post.
		 */
		model.addAttribute("owner",principal.getName());
		model.addAttribute("posts",postTeamServiceImpl.list());
		return "/postteams/list";
	}
	
	@RequestMapping("/view/{id}")
	public String postView(@PathVariable("id") Long id, Model model) {
		model.addAttribute("post",postTeamServiceImpl.get(id));	
		return "/postteams/view"; 
	}
	
	@RequestMapping("/create") 
	public String postCreate(Model model){
		PostTeam post = new PostTeam();
		post.setDeleteYN('N');
		model.addAttribute("post",post);
		return "/postteams/postForm";
	}
	
	@RequestMapping(value="/postForm", method= RequestMethod.POST)
	public String postEdit(@Valid @ModelAttribute("postSmallGroup") PostSmallGroup postSmallGroup, 
							 Model model,
							 BindingResult bindingResult){
		model.addAttribute("post",postSmallGroup);
		return "redirect:/postteams/list";
	}
	
	@RequestMapping(value ="/save", method = RequestMethod.POST )
	public String postSave(Principal principal, @Valid PostTeam postTeam, BindingResult bindingResult, Model model) {				
		/*if(bindingResult.hasErrors()){
			model.addAttribute("post", postSmallGroup);
			return "/postsmallgroups/postForm";
		} */
		postTeam.setMember(memberService.findByEmail(principal.getName()));
		postTeamServiceImpl.postSave(postTeam);
		return "redirect:/postteams/view/" + postTeam.getId();	
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/edit/{id}")
	public String postEdit(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("post",postTeamServiceImpl.get(id));
		return "/postteams/postForm";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/delete/{id}")
	public String postEdit(@PathVariable(value="id") Long id) {
		postTeamServiceImpl.postDelete(id);
		return "redirect:/postteams/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/byMember/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("posts", postTeamServiceImpl.listByMember(id));
		return "/postteams/list";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
