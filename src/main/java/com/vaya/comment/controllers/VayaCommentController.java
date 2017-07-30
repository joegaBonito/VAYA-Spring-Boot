package com.vaya.comment.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaya.comment.domain.VayaComment;
import com.vaya.comment.services.VayaCommentService;
import com.vaya.member.services.MemberService;
import com.vaya.postSmallGroup.services.PostSmallGroupService;
import com.vaya.postTeam.services.PostTeamService;

@Controller
public class VayaCommentController {
	
	private VayaCommentService vayaCommentService;
	private PostSmallGroupService postSmallGroupService;
	private PostTeamService postTeamService;
	private MemberService memberService;
	
	
	@Autowired
	public VayaCommentController(VayaCommentService vayaCommentService, PostSmallGroupService postSmallGroupService, PostTeamService postTeamService, MemberService memberService) {
		this.vayaCommentService = vayaCommentService; 
		this.postSmallGroupService = postSmallGroupService;
		this.postTeamService = postTeamService;
		this.memberService = memberService;
	}
	
	/*
	 * Below controls Small Group Posts' comments
	 */
	@RequestMapping(value="/postsmallgroups/comment/add/{id}", method=RequestMethod.POST)
	public String addPostSmallGroupComment(@PathVariable Long id, 
			@ModelAttribute("comment") VayaComment vayaComment, Principal principal) {
		vayaComment.setMember(memberService.findByEmail(principal.getName()));
		vayaComment.setPostSmallGroup(postSmallGroupService.get(id));
		vayaCommentService.addComment(vayaComment);
		return "redirect:/postsmallgroups/view/"+id;
	}
	
	@RequestMapping(value="/postsmallgroups/comment/edit/{postId}/{commentId}")
	public String editPostSmallGroupComment(Model model, @PathVariable(value="postId") Long postId, @PathVariable(value="commentId") Long commentId) {
		model.addAttribute("comment",vayaCommentService.get(commentId));
		model.addAttribute("post",postSmallGroupService.get(postId));
		return "/comment/editPostSmallGroupComment";
	}
	
	@RequestMapping(value="/postsmallgroups/comment/delete/{postId}/{commentId}")
	public String deletePostSmallGroupComment(Model model, @PathVariable(value="postId") Long postId, @PathVariable(value="commentId") Long commentId) {
		vayaCommentService.deleteComment(commentId);
		model.addAttribute("post",postSmallGroupService.get(postId));
		return "redirect:/postsmallgroups/view/"+postId;
	}
	
	/*
	 * Below controls Team Posts' comments
	 */
	@RequestMapping(value="/postteams/comment/add/{id}", method=RequestMethod.POST)
	public String addPostTeamComment(@PathVariable Long id, @ModelAttribute("comment") VayaComment vayaComment,Principal principal) {
		vayaComment.setMember(memberService.findByEmail(principal.getName()));
		vayaComment.setPostTeam(postTeamService.get(id));
		vayaCommentService.addComment(vayaComment);
		return "redirect:/postteams/view/"+id;
	}
	
	@RequestMapping(value="/postteams/comment/edit/{postId}/{commentId}")
	public String editPostTeamComment(Model model,@PathVariable(value="postId") Long postId, @PathVariable(value="commentId") Long commentId) {
		model.addAttribute("comment",vayaCommentService.get(commentId));
		model.addAttribute("post",postTeamService.get(postId));
		return "/comment/editPostTeamComment";
	}
	
	@RequestMapping(value="/postteams/comment/delete/{postId}/{commentId}")
	public String deleteTeamComment(Model model, @PathVariable(value="postId") Long postId, @PathVariable(value="commentId") Long commentId) {
		vayaCommentService.deleteComment(commentId);
		model.addAttribute("post",postTeamService.get(postId));
		return "redirect:/postteams/view/"+postId;
	}
	
}
