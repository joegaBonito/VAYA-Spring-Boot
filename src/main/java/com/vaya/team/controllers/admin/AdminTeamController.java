package com.vaya.team.controllers.admin;

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

import com.vaya.team.domain.Team;
import com.vaya.team.services.impl.TeamServiceImpl;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminTeamController {
	
	@Autowired
	private TeamServiceImpl teamServiceImpl;
	
	@RequestMapping("/admin/team/list")
	public String teamList(Model model,@PageableDefault(value=10) Pageable pageable) {
		model.addAttribute("teams",teamServiceImpl.teamList(pageable));
		return "/admin/team/list";
	}
	@RequestMapping("/admin/team/create") 
	public String teamCreate(Model model) {
		Team team = new Team();
		team.setDelete_YN('N');
		model.addAttribute("team",team);
		return "/admin/team/teamForm";
	}
	
	@RequestMapping(value="/admin/team/save", method=RequestMethod.POST)
	public String teamSave(@ModelAttribute("team") Team team) {
		teamServiceImpl.save(team);
		return "redirect:/admin/team/list";
	}
	
	@RequestMapping("/admin/team/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) {
		teamServiceImpl.delete(id);
		redirectAttrs.addFlashAttribute("message", "Team was deleted!");
		return "redirect:/admin/team/list";
	}
	@RequestMapping("/admin/team/edit/{id}")
	public String edit(@PathVariable Long id, Model model){
		model.addAttribute("team",teamServiceImpl.get(id));
		return "admin/team/teamEdit";		
	}
}
