package com.vaya.smallgroup.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String smallGroupList(Model model, @PageableDefault(value=10) Pageable pageable) {
		model.addAttribute("smallGroups",smallGroupServiceImpl.list(pageable));
		return "/admin/smallgroup/list";
	}
	@RequestMapping("/admin/smallgroup/create") 
	public String smallGroupCreate(Model model) {
		SmallGroup smallGroup = new SmallGroup();
		smallGroup.setDelete_YN('N');
		model.addAttribute("smallGroup",smallGroup);
		return "/admin/smallgroup/smallGroupForm";
	}
	
	@RequestMapping(value="/admin/smallgroup/save", method=RequestMethod.POST)
	public String smallGroupSave(@ModelAttribute("smallGroup") SmallGroup smallGroup) {
		smallGroupServiceImpl.save(smallGroup);
		return "redirect:/admin/smallgroup/list";
	}
	
	@RequestMapping("/admin/smallgroup/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		smallGroupServiceImpl.delete(id);
		redirectAttrs.addFlashAttribute("message", "Small Group was deleted!");
		return "redirect:/admin/smallgroup/list";
	}
	
	@RequestMapping("/admin/smallgroup/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("smallGroup",smallGroupServiceImpl.get(id));
		return "admin/smallgroup/smallGroupEdit";		
	}
}
