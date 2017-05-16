package com.vaya.postAccounting.controllers;

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

import com.vaya.etc.services.impl.EtcServiceImpl;
import com.vaya.meeting.services.impl.MeetingServiceImpl;
import com.vaya.member.services.MemberService;
import com.vaya.postAccounting.domain.PostAccounting;
import com.vaya.postAccounting.services.impl.PostAccountingServiceImpl;
import com.vaya.retreat.services.impl.RetreatServiceImpl;
import com.vaya.team.services.impl.TeamServiceImpl;

@Controller
@RequestMapping("/postaccountings")
public class PostAccountingController {
	
	private PostAccountingServiceImpl postAccountingServiceImpl;
	private MemberService memberService;
	private TeamServiceImpl teamServiceImpl;
	private EtcServiceImpl etcServiceImpl;
	private MeetingServiceImpl meetingServiceImpl;
	private RetreatServiceImpl retreatServiceImpl;
	

	@Autowired
	public PostAccountingController(PostAccountingServiceImpl postAccountingServiceImpl, MemberService memberService, TeamServiceImpl teamServiceImpl,
			EtcServiceImpl etcServiceImpl, MeetingServiceImpl meetingServiceImpl, RetreatServiceImpl retreatServiceImpl) {
		super();
		this.postAccountingServiceImpl = postAccountingServiceImpl;
		this.memberService = memberService;
		this.teamServiceImpl = teamServiceImpl;
		this.etcServiceImpl = etcServiceImpl;
		this.meetingServiceImpl = meetingServiceImpl;
		this.retreatServiceImpl = retreatServiceImpl;
	}

	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list")
	public String postList(Principal principal, Model model) {
		/*
		 * Used when logged in user's email is directly used as the owner of a
		 * post.
		 */
		model.addAttribute("owner",principal.getName());
		model.addAttribute("posts",postAccountingServiceImpl.list());
		return "/postaccountings/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/byMember/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("posts", postAccountingServiceImpl.listByMember(id));
		return "postaccountings/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/view/{id}")
	public String view(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("post",postAccountingServiceImpl.get(id));
		return "postaccountings/view";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("post",postAccountingServiceImpl.get(id));
		model.addAttribute("members", memberService.list());
		model.addAttribute("teams", teamServiceImpl.teamList());
		model.addAttribute("etcs", etcServiceImpl.etcList());
		model.addAttribute("meetings", meetingServiceImpl.meetingList());
		model.addAttribute("retreats", retreatServiceImpl.retreatList());
		return "postaccountings/postForm";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		postAccountingServiceImpl.delete(id);
		redirectAttrs.addFlashAttribute("message", "Post was deleted!");
		return "redirect:/postaccountings/list";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/create")
	public String create(Principal principal, Model model) {
		PostAccounting post = new PostAccounting();
		post.setApproval("pending");
		model.addAttribute("post",post);		 
		model.addAttribute("members", memberService.list());
		model.addAttribute("teams", teamServiceImpl.teamList());
		model.addAttribute("etcs", etcServiceImpl.etcList());
		model.addAttribute("meetings", meetingServiceImpl.meetingList());
		model.addAttribute("retreats", retreatServiceImpl.retreatList());
		return "/postaccountings/postForm";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid PostAccounting post, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("members", memberService.list());
			return "/postaccountings/postForm";
		} else {
			PostAccounting savedPost = postAccountingServiceImpl.save(post);
			return "redirect:/postaccountings/view/" + savedPost.getPostId();
		}

	}
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
