package com.vaya.smallgroup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.smallgroup.domain.SmallGroup;
import com.vaya.smallgroup.services.impl.SmallGroupServiceImpl;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminSmallGroupController {
	
	private SmallGroupServiceImpl smallGroupServiceImpl;
	
	@Autowired
	public AdminSmallGroupController(SmallGroupServiceImpl smallGroupServiceImpl) {
		this.smallGroupServiceImpl = smallGroupServiceImpl;
	}
	
	@RequestMapping("/admin/smallgroup/list")
	public String smallGroupList(Model model) {
		model.addAttribute("smallGroups",smallGroupServiceImpl.list());
		return "/admin/smallgroup/list";
	}
	@RequestMapping("/admin/smallgroup/create") 
	public String smallGroupCreate(Model model) {
		model.addAttribute("smallGroup",new SmallGroup());
		return "/admin/smallgroup/smallGroupForm";
	}
	
	@RequestMapping(value="/admin/smallgroup/save", method=RequestMethod.POST)
	public String smallGroupSave(@ModelAttribute("smallGroup") SmallGroup smallGroup) {
		smallGroupServiceImpl.save(smallGroup);
		return "redirect:/admin/smallgroup/list";
	}
}
