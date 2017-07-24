package com.vaya.member.controllers.admin;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaya.member.domain.Member;
import com.vaya.member.services.MemberService;
import com.vaya.smallgroup.services.impl.SmallGroupServiceImpl;
import com.vaya.team.services.impl.TeamServiceImpl;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminMembersController{

	private MemberService memberService;
	private TeamServiceImpl teamServiceImpl;
	private SmallGroupServiceImpl smallGroupServiceImpl;
	
	
	@Autowired
	public AdminMembersController(MemberService memberService, TeamServiceImpl teamServiceImpl, SmallGroupServiceImpl smallGroupServiceImpl) {
		this.memberService = memberService;
		this.teamServiceImpl =teamServiceImpl;
		this.smallGroupServiceImpl = smallGroupServiceImpl;
	}
	
	@RequestMapping("/admin/members")
	public String list(Model model,  @PageableDefault(value=10) Pageable pageable) {
		model.addAttribute("members", memberService.list(pageable));
		return "admin/members/list";
	}
	
	@RequestMapping("/admin/members/{id}")
	public String view(@PathVariable Long id, Model model) {
		model.addAttribute("member", memberService.get(id));
		return "admin/members/view";
	}
	
	// create | save
	@RequestMapping("/admin/member/create")
	public String create(Model model, @PageableDefault(value=10) Pageable pageable) {
		model.addAttribute("member", new Member());
		model.addAttribute("members", memberService.list(pageable));
		model.addAttribute("roles", memberService.roles());
		model.addAttribute("NoSmallGroup",smallGroupServiceImpl.get((long) 1).getId());
		model.addAttribute("NoTeam",teamServiceImpl.get((long) 1).getTeamId());
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
	public String edit(@PathVariable Long id, Model model,Pageable pageable){
		model.addAttribute("member",memberService.get(id));
		model.addAttribute("roles", memberService.roles());
		model.addAttribute("teams", teamServiceImpl.teamList(pageable));
		model.addAttribute("smallGroups", smallGroupServiceImpl.list(pageable));
		return "admin/members/memberEdit";		
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
