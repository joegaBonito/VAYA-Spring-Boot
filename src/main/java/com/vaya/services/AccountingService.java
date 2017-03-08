package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Accounting;
import com.vaya.domain.Etc;
import com.vaya.domain.Meeting;
import com.vaya.domain.Post;
import com.vaya.domain.Retreat;
import com.vaya.domain.Team;
import com.vaya.repositories.AccountingRepository;

@Service
public class AccountingService {
	
	@Autowired 
	private AccountingRepository accountingRepository;
	@Autowired
	private TeamService teamService;
	@Autowired
	private PostService postService;
	@Autowired
	private EtcService etcService;
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private RetreatService retreatService;
	
	public AccountingService(AccountingRepository accountingRepository, 
							 TeamService teamService, 
							 PostService postService,
							 EtcService etcService,
							 MeetingService meetingService,
							 RetreatService retreatService) {
		this.accountingRepository = accountingRepository;
		this.teamService = teamService;
		this.postService = postService;
		this.etcService = etcService;
		this.meetingService = meetingService;
		this.retreatService = retreatService;
	}

	public List<Accounting> list() {
		return accountingRepository.findByOrderByAccountingId();
	}

	public void save(Accounting accounting) {
		accountingRepository.save(accounting);
	}

	public double getTotalCurrentBudget() {
		double num = 0;
		for(Team team : teamService.teamList()){
			num += team.getAccounting().getCurrentBudget();
		}
		for(Etc etc : etcService.etcList()){
			num += etc.getAccounting().getCurrentBudget();
		}
		for(Meeting meeting : meetingService.meetingList()){
			num += meeting.getAccounting().getCurrentBudget();
		}
		for(Retreat retreat : retreatService.retreatList()){
			num += retreat.getAccounting().getCurrentBudget();
		}
		return num;
	}
	
	public void getBalanceAfterTeamExpense(){
		for(Team team : teamService.teamList()) {
			double num = 0;
			for(Post post : postService.list()) {
				if(post.getTeam() == null) {
					continue;
				}
				else if(team.getTeamName().equals(post.getTeam().getTeamName()))
					num += post.getQuantity();
			}
		team.getAccounting().setCurrentBudget(team.getAccounting().getTotalBudget() - num);
		teamService.save(team);
		}
	}
	public void getBalanceAfterEtcExpense(){
		for(Etc etc : etcService.etcList()) {
			double num = 0;
			for(Post post : postService.list()) {
				if(post.getEtc() == null) {
					continue;
				}
				else if(etc.getEtcName().equals(post.getEtc().getEtcName()))
					num += post.getQuantity();
			}
		etc.getAccounting().setCurrentBudget(etc.getAccounting().getTotalBudget() - num);
		etcService.save(etc);
		}
	}
	public void getBalanceAfterMeetingExpense(){
		for(Meeting meeting : meetingService.meetingList()) {
			double num = 0;
			for(Post post : postService.list()) {
				if(post.getMeeting() == null) {
					continue;
				}
				else if(meeting.getMeetingName().equals(post.getMeeting().getMeetingName()))
					num += post.getQuantity();
			}
		meeting.getAccounting().setCurrentBudget(meeting.getAccounting().getTotalBudget() - num);
		meetingService.save(meeting);
		}
	}
	public void getBalanceAfterRetreatExpense(){
		for(Retreat retreat : retreatService.retreatList()) {
			double num = 0;
			for(Post post : postService.list()) {
				if(post.getRetreat() == null) {
					continue; 
				}
				else if(retreat.getRetreatName().equals(post.getRetreat().getRetreatName()))
					num += post.getQuantity();
			}
		retreat.getAccounting().setCurrentBudget(retreat.getAccounting().getTotalBudget() - num);
		retreatService.save(retreat);
		}
	}
	public void getBalanceAfterIncome() {
		
	}
}
