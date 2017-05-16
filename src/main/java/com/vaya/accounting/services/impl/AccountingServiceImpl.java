package com.vaya.accounting.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.accounting.domain.Accounting;
import com.vaya.accounting.repositories.AccountingRepository;
import com.vaya.accounting.services.AccountingService;
import com.vaya.etc.domain.Etc;
import com.vaya.etc.services.impl.EtcServiceImpl;
import com.vaya.meeting.domain.Meeting;
import com.vaya.meeting.services.impl.MeetingServiceImpl;
import com.vaya.postAccounting.domain.PostAccounting;
import com.vaya.postAccounting.services.impl.PostAccountingServiceImpl;
import com.vaya.retreat.domain.Retreat;
import com.vaya.retreat.services.impl.RetreatServiceImpl;
import com.vaya.team.domain.Team;
import com.vaya.team.services.impl.TeamServiceImpl;

@Service
public class AccountingServiceImpl implements AccountingService{
	
	@Autowired 
	private AccountingRepository accountingRepository;
	@Autowired
	private TeamServiceImpl teamServiceImpl;
	@Autowired
	private PostAccountingServiceImpl postAccountingServiceImpl;
	@Autowired
	private EtcServiceImpl etcServiceImpl;
	@Autowired
	private MeetingServiceImpl meetingServiceImpl;
	@Autowired
	private RetreatServiceImpl retreatServiceImpl;
	
	public AccountingServiceImpl(AccountingRepository accountingRepository, 
							 TeamServiceImpl teamServiceImpl, 
							 PostAccountingServiceImpl postAccountingServiceImpl,
							 EtcServiceImpl etcServiceImpl,
							 MeetingServiceImpl meetingServiceImpl,
							 RetreatServiceImpl retreatServiceImpl) {
		this.accountingRepository = accountingRepository;
		this.teamServiceImpl = teamServiceImpl;
		this.postAccountingServiceImpl = postAccountingServiceImpl;
		this.etcServiceImpl = etcServiceImpl;
		this.meetingServiceImpl = meetingServiceImpl;
		this.retreatServiceImpl = retreatServiceImpl;
	}

	public List<Accounting> list() {
		return accountingRepository.findByOrderByAccountingId();
	}

	public void save(Accounting accounting) {
		accountingRepository.save(accounting);
	}

	public double getTotalCurrentBudget() {
		double num = 0;
		for(Team team : teamServiceImpl.teamList()){
			num += team.getAccounting().getCurrentBudget();
		}
		for(Etc etc : etcServiceImpl.etcList()){
			num += etc.getAccounting().getCurrentBudget();
		}
		for(Meeting meeting : meetingServiceImpl.meetingList()){
			num += meeting.getAccounting().getCurrentBudget();
		}
		for(Retreat retreat : retreatServiceImpl.retreatList()){
			num += retreat.getAccounting().getCurrentBudget();
		}
		return num;
	}
	
	public void getBalanceAfterTeamExpense(){
		for(Team team : teamServiceImpl.teamList()) {
			double num = 0;
			for(PostAccounting post : postAccountingServiceImpl.list()) {
				if(post.getTeam() == null) {
					continue;
				}
				else if(team.getTeamName().equals(post.getTeam().getTeamName()))
					num += post.getQuantity();
			}
		team.getAccounting().setCurrentBudget(team.getAccounting().getTotalBudget() - num);
		teamServiceImpl.save(team);
		}
	}
	public void getBalanceAfterEtcExpense(){
		for(Etc etc : etcServiceImpl.etcList()) {
			double num = 0;
			for(PostAccounting post : postAccountingServiceImpl.list()) {
				if(post.getEtc() == null) {
					continue;
				}
				else if(etc.getEtcName().equals(post.getEtc().getEtcName()))
					num += post.getQuantity();
			}
		etc.getAccounting().setCurrentBudget(etc.getAccounting().getTotalBudget() - num);
		etcServiceImpl.save(etc);
		}
	}
	public void getBalanceAfterMeetingExpense(){
		for(Meeting meeting : meetingServiceImpl.meetingList()) {
			double num = 0;
			for(PostAccounting post : postAccountingServiceImpl.list()) {
				if(post.getMeeting() == null) {
					continue;
				}
				else if(meeting.getMeetingName().equals(post.getMeeting().getMeetingName()))
					num += post.getQuantity();
			}
		meeting.getAccounting().setCurrentBudget(meeting.getAccounting().getTotalBudget() - num);
		meetingServiceImpl.save(meeting);
		}
	}
	public void getBalanceAfterRetreatExpense(){
		for(Retreat retreat : retreatServiceImpl.retreatList()) {
			double num = 0;
			for(PostAccounting post : postAccountingServiceImpl.list()) {
				if(post.getRetreat() == null) {
					continue; 
				}
				else if(retreat.getRetreatName().equals(post.getRetreat().getRetreatName()))
					num += post.getQuantity();
			}
		retreat.getAccounting().setCurrentBudget(retreat.getAccounting().getTotalBudget() - num);
		retreatServiceImpl.save(retreat);
		}
	}
	public void getBalanceAfterIncome() {
		
	}
}
