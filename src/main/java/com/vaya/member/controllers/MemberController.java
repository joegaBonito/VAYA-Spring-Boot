package com.vaya.member.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.member.domain.Member;
import com.vaya.member.services.MemberService;
import com.vaya.smallgroup.services.impl.SmallGroupServiceImpl;
import com.vaya.team.services.impl.TeamServiceImpl;


@Controller
public class MemberController {
	
	private MemberService memberService;
	private SmallGroupServiceImpl smallGroupServiceImpl;
	private TeamServiceImpl teamServiceImpl;
	
	@Autowired
	public MemberController(MemberService memberService, SmallGroupServiceImpl smallGroupServiceImpl,TeamServiceImpl teamServiceImpl) {
		super();
		this.memberService = memberService;
		this.smallGroupServiceImpl = smallGroupServiceImpl;
		this.teamServiceImpl = teamServiceImpl;
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/members/list")
	public String list(Principal principal,Model model, @PageableDefault(value=10) Pageable pageable){
		model.addAttribute("members", memberService.list(pageable));
		return "/members/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/members/view")
	public String view(Model model){
		return "/members/view";
	}
	
	@RequestMapping("/members/createMember")
	public String createAuthor(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		model.addAttribute("NoSmallGroup",smallGroupServiceImpl.get((long) 1).getId());
		model.addAttribute("NoTeam",teamServiceImpl.get((long) 1).getTeamId());
		return "auth/createaccount";
	}
	
	@RequestMapping(value = "/members/processForm", method = RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult, Model model,@PageableDefault Pageable pageable) {
		if(bindingResult.hasErrors()) {
			return "/auth/createaccount";
		}
		if(!member.getPassword().equals(member.getConfirmPassword())) {
			
		}
		for(Member m : memberService.list(pageable)) {
			if(member.getEmail().equals(m.getEmail())) {
				final String errorMessageEmail = "errorMessageEmail";
				model.addAttribute("errorMessageEmail",errorMessageEmail);
				return "/auth/createaccount";
			}
		}
		
		memberService.create(member);
		return "auth/login";
	}
	
	@RequestMapping("/members/editUserInfo")
	public String editUserInfo(Principal principal, Model model,@PageableDefault(value=10) Pageable pageable){
		model.addAttribute("member",memberService.editUserinfo(principal,pageable));
		model.addAttribute("roles", memberService.roles());
		return "/members/memberForm";		
	}
	
	@RequestMapping(value ="/members/save", method = RequestMethod.POST )
	public String editSave(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult, Model model) {				
		if(bindingResult.hasErrors()){
			model.addAttribute("roles", memberService.roles());
			return "/members/memberForm";
		} 
		memberService.editSave(member);
	    return "redirect:/members/list";	
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}
}
