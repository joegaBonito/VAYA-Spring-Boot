package com.vaya.postSmallGroup.controllers;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.vaya.comment.domain.VayaComment;
import com.vaya.comment.services.VayaCommentService;
import com.vaya.general.domain.Role;
import com.vaya.member.domain.Member;
import com.vaya.member.services.MemberService;
import com.vaya.postSmallGroup.domain.PostSmallGroup;
import com.vaya.postSmallGroup.services.PostSmallGroupService;

@Controller
@RequestMapping("/postsmallgroups")
public class PostSmallGroupController {
	
	private MemberService memberService;
	private PostSmallGroupService postSmallGroupService;
	private VayaCommentService vayaCommentService;
	
	@Autowired
	public PostSmallGroupController(PostSmallGroupService postSmallGroupService, MemberService memberService,VayaCommentService vayaCommentService) {
		this.postSmallGroupService = postSmallGroupService; 
		this.memberService = memberService;
		this.vayaCommentService = vayaCommentService;
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list") 
	public String postList(Principal principal, Model model, @PageableDefault(value=10) Pageable pageable) {
		/*
		 * This method only allows the same small group members to see their posts.
		 */
		
		for(Member member: memberService.listWithoutPagination()) {
			if(member.getEmail().equals(principal.getName())) {
				Page<PostSmallGroup> posts =  postSmallGroupService.list(member.getSmallGroup().getId(),pageable);
				model.addAttribute("posts",posts);
				model.addAttribute("role",member.getRole());
			}
		}
		model.addAttribute("owner",principal.getName());
		return "/postsmallgroups/list";
	}
	
	@RequestMapping("/view/{id}")
	public String postView(@PathVariable("id") Long id, Model model, @PageableDefault(value=5) Pageable pageable,Principal principal) {
		model.addAttribute("post",postSmallGroupService.get(id));	
		model.addAttribute("comments", vayaCommentService.commentPostSmallGroupList(id,pageable));
		model.addAttribute("comment", new VayaComment());
		Member member = memberService.findByEmail(principal.getName());
		model.addAttribute("role",member.getRole());
		model.addAttribute("owner",principal.getName());
		return "/postsmallgroups/view"; 
	}
	
	@RequestMapping("/create") 
	public String postCreate(Model model){
		PostSmallGroup post = new PostSmallGroup();
		post.setDeleteYN('N');
		model.addAttribute("post",post);
		return "/postsmallgroups/postForm";
	}
	
	@RequestMapping(value="/postForm", method= RequestMethod.POST)
	public String postEdit(@Valid @ModelAttribute("postSmallGroup") PostSmallGroup postSmallGroup, 
							Principal principal,
							 Model model,
							 BindingResult bindingResult,@PageableDefault(value=10) Pageable pageable){
		/*
		 * This method sets the smallGroup id to the edited postSmallGroup entity.
		 */
		for(Member member: memberService.list(pageable)) {
			if(member.getEmail().equals(principal.getName())) {
				postSmallGroup.setSmallGroup(member.getSmallGroup());
			}
		}
		model.addAttribute("post",postSmallGroup);
		return "redirect:/postsmallgroups/list";
	}
	
	@RequestMapping(value ="/save", method = RequestMethod.POST )
	public String postSave(Principal principal, @Valid PostSmallGroup postSmallGroup, BindingResult bindingResult, Model model,@PageableDefault(value=10) Pageable pageable) {				
		/*if(bindingResult.hasErrors()){
			model.addAttribute("post", postSmallGroup);
			return "/postsmallgroups/postForm";
		} */
		postSmallGroup.setMember(memberService.findByEmail(principal.getName()));
		/*
		 * This method sets the smallGroup id to the newly created postSmallGroup entity.
		 */
		for(Member member: memberService.list(pageable)) {
			if(member.getEmail().equals(principal.getName())) {
				postSmallGroup.setSmallGroup(member.getSmallGroup());
			}
		}
		postSmallGroupService.postSave(postSmallGroup);
		return "redirect:/postsmallgroups/view/" + postSmallGroup.getId();	
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/edit/{id}")
	public String postEdit(Principal principal, @PathVariable(value="id") Long id, Model model) {
		Member member = memberService.findByEmail(principal.getName());
		if(postSmallGroupService.get(id).getMember().getEmail().equals(principal.getName()) || member.getRole().equals("ADMIN")) {
			model.addAttribute("post",postSmallGroupService.get(id));
			return "/postsmallgroups/postForm";
		}
		else {
			return "redirect:/postsmallgroups/list";
		} 
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/delete/{id}")
	public String postEdit(Principal principal, @PathVariable(value="id") Long id) {
		Member member = memberService.findByEmail(principal.getName());
		if(postSmallGroupService.get(id).getMember().getEmail().equals(principal.getName()) || member.getRole().equals("ADMIN")){
			postSmallGroupService.postDelete(id);
			return "redirect:/postsmallgroups/list";
		} else {
			return "redirect:/postsmallgroups/list";
		}
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/byMember/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model,Principal principal,@PageableDefault(value=10) Pageable pageable){
		/*
		 * This method only allows the same small group members to see their posts.
		 */
		for(Member member: memberService.list(pageable)) {
			if(member.getEmail().equals(principal.getName())) {
				model.addAttribute("OwnerSmallGroup",member.getSmallGroup());
			}
		}
		Page<PostSmallGroup> posts = postSmallGroupService.listByMember(id,pageable);
		model.addAttribute("posts", posts);
		return "/postsmallgroups/list";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
