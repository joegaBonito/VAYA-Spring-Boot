package com.vaya.controllers.admin;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaya.domain.Member;
import com.vaya.services.MemberService;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminMembersController{

	private MemberService memberService;
	
	
	@Autowired
	public AdminMembersController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping("/admin/members")
	public String list(Model model) {
		model.addAttribute("members", memberService.list());
		return "admin/members/list";
	}
	
	@RequestMapping("/admin/members/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberService.get(id));
		return "admin/members/view";
	}
	
	// create | save
	@RequestMapping("/admin/member/create")
	public String create(Model model) {
		model.addAttribute("member", new Member());
		model.addAttribute("members", memberService.list());
		model.addAttribute("roles", memberService.roles());
		return "admin/members/memberForm";
	}
	@RequestMapping(value = "/admin/members/save", method = RequestMethod.POST )
	public String editSave(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult, Model model) {				
		if(bindingResult.hasErrors()){
			model.addAttribute("roles", memberService.roles());
			return "/admin/members/memberForm";
		} 
		memberService.editSave(member);
	    return "redirect:/admin/members/";	
	}
	
	@RequestMapping("/admin/members/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("member",memberService.get(id));
		model.addAttribute("roles", memberService.roles());
		return "admin/members/memberForm";		
	}
	
	
	@RequestMapping("/admin/members/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		memberService.delete(id);
		redirectAttrs.addFlashAttribute("message", "Member was deleted!");
		return "redirect:/admin/members";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
	}
}
