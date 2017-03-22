package com.vaya.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.domain.Accounting;
import com.vaya.services.AccountingService;
import com.vaya.services.EtcService;
import com.vaya.services.MeetingService;
import com.vaya.services.RetreatService;
import com.vaya.services.TeamService;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminAccountingController {
	
	private AccountingService accountingService;
	private TeamService teamService;
	private EtcService etcService;
	private MeetingService meetingService;
	private RetreatService retreatService;
	
	@Autowired
	public AdminAccountingController(AccountingService accountingService, 
			                         TeamService teamService,
			                         EtcService etcService,
			                         MeetingService meetingService,
			                         RetreatService retreatService) {
		this.accountingService = accountingService;
		this.teamService = teamService;
		this.etcService = etcService;
		this.meetingService = meetingService;
		this.retreatService = retreatService;
	}
	
	@RequestMapping("/admin/accountings")
	public String adminAccounting(Model model) {
		accountingService.getBalanceAfterTeamExpense();
		accountingService.getBalanceAfterEtcExpense();
		accountingService.getBalanceAfterMeetingExpense();
		accountingService.getBalanceAfterRetreatExpense();
		model.addAttribute("accountingTotalCurrentBudget",accountingService.getTotalCurrentBudget());
		model.addAttribute("teams", teamService.teamList());
		model.addAttribute("etcs", etcService.etcList());
		model.addAttribute("meetings",meetingService.meetingList());
		model.addAttribute("retreats",retreatService.retreatList());
		return "/admin/accountings/list";
	}
	
	@RequestMapping(value="/admin/accounting/save", method=RequestMethod.POST)
	public String adminAccountingCreate(@ModelAttribute("accounting") Accounting accounting) {
		accountingService.save(accounting);
		return "redirect:/admin/accountings/";
	}
}
