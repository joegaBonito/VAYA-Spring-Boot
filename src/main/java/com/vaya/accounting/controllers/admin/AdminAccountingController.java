package com.vaya.accounting.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya.accounting.domain.Accounting;
import com.vaya.accounting.services.impl.AccountingServiceImpl;
import com.vaya.etc.services.impl.EtcServiceImpl;
import com.vaya.meeting.services.impl.MeetingServiceImpl;
import com.vaya.retreat.services.impl.RetreatServiceImpl;
import com.vaya.team.services.impl.TeamServiceImpl;

@Controller
@Secured({"ROLE_ADMIN"})
public class AdminAccountingController {
	
	private AccountingServiceImpl accountingServiceImpl;
	private TeamServiceImpl teamServiceImpl;
	private EtcServiceImpl etcServiceImpl;
	private MeetingServiceImpl meetingServiceImpl;
	private RetreatServiceImpl retreatServiceImpl;
	
	public AdminAccountingController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public AdminAccountingController(AccountingServiceImpl accountingServiceImpl, 
			                         TeamServiceImpl teamServiceImpl,
			                         EtcServiceImpl etcServiceImpl,
			                         MeetingServiceImpl meetingServiceImpl,
			                         RetreatServiceImpl retreatServiceImpl) {
		this.accountingServiceImpl = accountingServiceImpl;
		this.teamServiceImpl = teamServiceImpl;
		this.etcServiceImpl = etcServiceImpl;
		this.meetingServiceImpl = meetingServiceImpl;
		this.retreatServiceImpl = retreatServiceImpl;
	}
	
	@RequestMapping("/admin/accountings")
	public String adminAccounting(Model model,Pageable pageable) {
		accountingServiceImpl.getBalanceAfterTeamExpense(pageable);
		accountingServiceImpl.getBalanceAfterEtcExpense();
		accountingServiceImpl.getBalanceAfterMeetingExpense();
		accountingServiceImpl.getBalanceAfterRetreatExpense();
		model.addAttribute("accountingTotalCurrentBudget",accountingServiceImpl.getTotalCurrentBudget(pageable));
		model.addAttribute("teams", teamServiceImpl.teamList(pageable));
		model.addAttribute("etcs", etcServiceImpl.etcList());
		model.addAttribute("meetings",meetingServiceImpl.meetingList());
		model.addAttribute("retreats",retreatServiceImpl.retreatList());
		return "/admin/accountings/list";
	}
	
	@RequestMapping(value="/admin/accounting/save", method=RequestMethod.POST)
	public String adminAccountingCreate(@ModelAttribute("accounting") Accounting accounting) {
		accountingServiceImpl.save(accounting);
		return "redirect:/admin/accountings/";
	}
}
