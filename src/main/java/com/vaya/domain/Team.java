package com.vaya.domain;

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
	private Set<Post> post;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="team")
	private Master masterId;

	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Team(Long teamId, String teamName, Accounting accounting, Set<Post> post, Master masterId) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.accounting = accounting;
		this.post = post;
		this.masterId = masterId;
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

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}

	public Master getMasterId() {
		return masterId;
	}

	public void setMasterId(Master masterId) {
		this.masterId = masterId;
	}
}
