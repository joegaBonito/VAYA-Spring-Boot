package com.vaya.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Post {
	@Id 
	@GeneratedValue
	@Column(name="id")
	private Long postId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="posted_on")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="MM/dd/yyyy HH:mm")
	private Date postedOn = new Date();
	
	@Column(name="quantity")
	private double quantity;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(nullable=true, name="team_id")
	private Team team;
	
	@ManyToOne
	@JoinColumn(nullable=true, name="retreat_id")
	private Retreat retreat;
	
	@ManyToOne
	@JoinColumn(nullable=true, name="etc_id")
	private Etc etc;
	
	@ManyToOne
	@JoinColumn(name="meeting_id")
	private Meeting meeting;
	
	@NotEmpty
	@Column(columnDefinition = "TEXT")
	private String body;

	public Post(Long postId, String title, Date postedOn, double quantity, Member member, Team team, Retreat retreat,
			Etc etc, Meeting meeting, String body) {
		super();
		this.postId = postId;
		this.title = title;
		this.postedOn = postedOn;
		this.quantity = quantity;
		this.member = member;
		this.team = team;
		this.retreat = retreat;
		this.etc = etc;
		this.meeting = meeting;
		this.body = body;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Retreat getRetreat() {
		return retreat;
	}

	public void setRetreat(Retreat retreat) {
		this.retreat = retreat;
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
