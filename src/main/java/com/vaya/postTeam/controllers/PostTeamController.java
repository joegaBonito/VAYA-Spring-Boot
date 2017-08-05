package com.vaya.postTeam.controllers;

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
import com.vaya.postTeam.domain.PostTeam;
import com.vaya.postTeam.services.PostTeamService;

@Controller
@RequestMapping("/postteams")
public class PostTeamController {
	
	private MemberService memberService;
	private PostTeamService postTeamService;
	private VayaCommentService vayaCommentService;
	
	@Autowired
	public PostTeamController(PostTeamService postTeamService, MemberService memberService,VayaCommentService vayaCommentService) {
		this.postTeamService = postTeamService; 
		this.memberService = memberService;
		this.vayaCommentService = vayaCommentService;
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list") 
	public String postList(Principal principal, Model model,@PageableDefault(value=10) Pageable pageable ) {
		/*
		 * This method only allows the same small group members to see their posts.
		 */
		for(Member member: memberService.listWithoutPagination()) {
			if(member.getEmail().equals(principal.getName())) {
				model.addAttribute("OwnerTeam",member.getTeam());
				Page<PostTeam> posts =  postTeamService.list(member.getTeam().getTeamId(),pageable);
				if(posts.getTotalElements()==0) {
					final String errorMessagePostEmpty = "No Posts Found.";
					model.addAttribute("errorMessagePostEmpty",errorMessagePostEmpty);
				}
				model.addAttribute("posts",posts);
				model.addAttribute("role",member.getRole());
			}
		}
		model.addAttribute("owner",principal.getName());
		return "/postteams/list";
	}
	
	@RequestMapping("/view/{id}")
	public String postView(@PathVariable("id") Long id, Model model,@PageableDefault(value=5) Pageable pageable, Principal principal) {
		model.addAttribute("post",postTeamService.get(id));	
		model.addAttribute("comments",vayaCommentService.commentPostTeamList(id,pageable));
		model.addAttribute("comment", new VayaComment());
		Member member = memberService.findByEmail(principal.getName());
		model.addAttribute("role",member.getRole());
		model.addAttribute("owner",principal.getName());
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
	public String postSave(Principal principal, @Valid PostTeam postTeam, BindingResult bindingResult, Model model,@PageableDefault(value=10) Pageable pageable) {				
		/*if(bindingResult.hasErrors()){
			model.addAttribute("post", postSmallGroup);
			return "/postsmallgroups/postForm";
		} */
		postTeam.setMember(memberService.findByEmail(principal.getName()));
		/*
		 * This method sets the smallGroup id to the newly created postSmallGroup entity.
		 */
		for(Member member: memberService.list(pageable)) {
			if(member.getEmail().equals(principal.getName())) {
				postTeam.setTeam(member.getTeam());
			}
		}
		postTeamService.postSave(postTeam);
		return "redirect:/postteams/view/" + postTeam.getId();	
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/edit/{id}")
	public String postEdit(Principal principal, @PathVariable(value="id") Long id, Model model) {
		Member member = memberService.findByEmail(principal.getName());
		if(postTeamService.get(id).getMember().getEmail().equals(principal.getName()) || member.getRole().equals("ADMIN")) {
			model.addAttribute("post",postTeamService.get(id));
			return "/postteams/postForm";
		}
		else {
			return "redirect:/postteams/list";
		}
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/delete/{id}")
	public String postDelete(Principal principal, @PathVariable(value="id") Long id) {
		Member member = memberService.findByEmail(principal.getName());
		if(postTeamService.get(id).getMember().getEmail().equals(principal.getName()) || member.getRole().equals("ADMIN")) {
			postTeamService.postDelete(id);
			return "redirect:/postteams/list";
		} else {
			return "redirect:/postteams/list";
		}
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/byMember/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model, Principal principal, @PageableDefault(value=10) Pageable pageable){
		Page<PostTeam> posts =  postTeamService.listByMember(id,pageable);
		model.addAttribute("posts", posts);
		/*
		 * This method only allows the same small group members to see their posts.
		 */
		for(Member member: memberService.list(pageable)) {
			if(member.getEmail().equals(principal.getName())) {
				model.addAttribute("OwnerTeam",member.getTeam());
			}
		}
		return "/postteams/list";
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
