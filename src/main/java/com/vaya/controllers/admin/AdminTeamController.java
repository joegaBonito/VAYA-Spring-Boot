package com.vaya.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.domain.Team;
import com.vaya.services.TeamService;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminTeamController {
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping("/admin/team/list")
	public String teamList(Model model) {
		model.addAttribute("teams",teamService.teamList());
		return "/admin/team/list";
	}
	@RequestMapping("/admin/team/create") 
	public String teamCreate(Model model) {
		model.addAttribute("team",new Team());
		return "/admin/team/teamForm";
	}
	
	@RequestMapping(value="/admin/team/save", method=RequestMethod.POST)
	public String teamSave(@ModelAttribute("team") Team team) {
		teamService.save(team);
		return "redirect:/admin/team/list";
	}
}
