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
import javax.persistence.Table;

@Entity
@Table(name="Meeting")
public class Meeting {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long meetingId;
	
	@Column(name="name")
	private String meetingName;
	
	@ManyToOne
	@JoinColumn(name="accounting_id")
	private Accounting accounting;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="meeting")
	private Set<Post> post;

	public Meeting(Long meetingId, String meetingName, Accounting accounting, Set<Post> post) {
		super();
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.accounting = accounting;
		this.post = post;
	}
	
	public Meeting() {}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
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
}
