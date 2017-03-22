package com.vaya.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="accounting")
public class Accounting {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long accountingId;
	
	@Column(name="current_budget")
	private double currentBudget;

	@Column(name="total_budget")
	private double totalBudget;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="accounting")
	private List<Team> team;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="accounting")
	private List<Meeting> meeting;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="accounting")
	private List<Etc> etc;

	@OneToMany(cascade={CascadeType.ALL},mappedBy="accounting")
	private List<Retreat> retreat;

	public Accounting(Long accountingId, double currentBudget, double totalBudget, List<Team> team,
			List<Meeting> meeting, List<Etc> etc, List<Retreat> retreat) {
		super();
		this.accountingId = accountingId;
		this.currentBudget = currentBudget;
		this.totalBudget = totalBudget;
		this.team = team;
		this.meeting = meeting;
		this.etc = etc;
		this.retreat = retreat;
	}

	public Accounting() {
		// TODO Auto-generated constructor stub
	}

	public Long getAccountingId() {
		return accountingId;
	}

	public void setAccountingId(Long accountingId) {
		this.accountingId = accountingId;
	}

	public double getCurrentBudget() {
		return currentBudget;
	}

	public void setCurrentBudget(double currentBudget) {
		this.currentBudget = currentBudget;
	}

	public double getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}

	public List<Team> getTeam() {
		return team;
	}

	public void setTeam(List<Team> team) {
		this.team = team;
	}

	public List<Meeting> getMeeting() {
		return meeting;
	}

	public void setMeeting(List<Meeting> meeting) {
		this.meeting = meeting;
	}

	public List<Etc> getEtc() {
		return etc;
	}

	public void setEtc(List<Etc> etc) {
		this.etc = etc;
	}

	public List<Retreat> getRetreat() {
		return retreat;
	}

	public void setRetreat(List<Retreat> retreat) {
		this.retreat = retreat;
	}
}

