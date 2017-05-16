package com.vaya.meeting.domain;

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
import com.vaya.master.domain.Master;
import com.vaya.postAccounting.domain.PostAccounting;

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
	private Set<PostAccounting> postAccounting;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="meeting")
	private Master masterId;

	public Meeting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Meeting(Long meetingId, String meetingName, Accounting accounting, Set<PostAccounting> postAccounting, Master masterId) {
		super();
		this.meetingId = meetingId;
		this.meetingName = meetingName;
		this.accounting = accounting;
		this.postAccounting = postAccounting;
		this.masterId = masterId;
	}

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
}
