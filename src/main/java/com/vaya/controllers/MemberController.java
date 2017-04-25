package com.vaya.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.domain.Member;
import com.vaya.domain.Role;
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
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list")
	public String list(Principal principal,Model model){
		model.addAttribute("userId",memberService.findIdByUsername(principal.getName()));
		model.addAttribute("members", memberService.list());
		return "/members/list";
	}
	
	@Secured({"ROLE_GUEST","ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/view")
	public String view(Model model){
		return "/members/view";
	}
	
	@RequestMapping("/createMember")
	public String createAuthor(Model model) {
		Member member = new Member();
		member.setRole("GUEST");
		model.addAttribute("member", member);
		return "auth/createaccount";
	}
	
	@RequestMapping(value = "/processForm", method = RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "/auth/createaccount";
		}
		if(!member.getPassword().equals(member.getConfirmPassword())) {
			return "/auth/createaccount";
		}
		memberService.save(member);
		return "auth/login";
	}
	
	@RequestMapping("/edit/{userId}")
	public String edit(@PathVariable Long userId, Model model){
		model.addAttribute("member",memberService.get(userId));
		model.addAttribute("roles", memberService.roles());
		return "/members/memberForm";		
	}
	
	@RequestMapping(value ="/save", method = RequestMethod.POST )
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
