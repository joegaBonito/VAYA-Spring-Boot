package com.vaya.team.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vaya.accounting.domain.Accounting;
import com.vaya.postTeam.domain.PostTeam;
import com.vaya.master.domain.Master;
import com.vaya.postAccounting.domain.PostAccounting;

@Entity
@Table(name="team")
public class Team {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long teamId;
	
	@Column(name="name")
	private String teamName;
	
	@ManyToOne
	@JoinColumn(nullable=true,name="accounting_id")
	private Accounting accounting;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="team")
	private Set<PostAccounting> postAccounting;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="team")
	private Master masterId;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="team")
	private List<PostTeam> postTeam;
	
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Team(Long teamId, String teamName, Accounting accounting, Set<PostAccounting> postAccounting, Master masterId, List<PostTeam> postTeam) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.accounting = accounting;
		this.postAccounting = postAccounting;
		this.masterId = masterId;
		this.postTeam =postTeam;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Accounting getAccounting() {
		return accounting;
	}

	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}

	public Set<PostAccounting> getPostAccounting() {
		return postAccounting;
	}

	public void setPostAccounting(Set<PostAccounting> postAccounting) {
		this.postAccounting = postAccounting;
	}

	public Master getMasterId() {
		return masterId;
	}

	public void setMasterId(Master masterId) {
		this.masterId = masterId;
	}

	public List<PostTeam> getPostTeam() {
		return postTeam;
	}

	public void setPostTeam(List<PostTeam> postTeam) {
		this.postTeam = postTeam;
	}
}
