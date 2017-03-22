package com.vaya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.domain.Member;
import com.vaya.services.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list")
	public String list(Model model){
		model.addAttribute("members", memberService.list());
		return "members/list";
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/view")
	public String view(Model model){
		return "members/view";
	}
	
	@RequestMapping("/createMember")
	public String createAuthor(Model model) {
		model.addAttribute("member", new Member());
		return "auth/createaccount";
	}
	
	@RequestMapping(value = "/processForm", method = RequestMethod.POST)
	public String processForm(@ModelAttribute("member") Member member, Model model) {
		memberService.save(member);
		return "auth/login";
	}
}
