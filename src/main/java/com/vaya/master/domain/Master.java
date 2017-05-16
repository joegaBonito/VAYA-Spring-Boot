package com.vaya.master.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vaya.etc.domain.Etc;
import com.vaya.meeting.domain.Meeting;
import com.vaya.retreat.domain.Retreat;
import com.vaya.team.domain.Team;

@Entity
@Table(name="master")
public class Master {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="team")
	private Team team;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="etc")
	private Etc etc;

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="meeting")
	private Meeting meeting;

	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="retreat")
	private Retreat retreat;
	
	public Master() {}

	public Master(Long id, Team team, Etc etc, Meeting meeting, Retreat retreat) {
		super();
		this.id = id;
		this.team = team;
		this.etc = etc;
		this.meeting = meeting;
		this.retreat = retreat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Etc getEtc() {
		return etc;
	}

	public void setEtc(Etc etc) {
		this.etc = etc;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Retreat getRetreat() {
		return retreat;
	}

	public void setRetreat(Retreat retreat) {
		this.retreat = retreat;
	}
}
