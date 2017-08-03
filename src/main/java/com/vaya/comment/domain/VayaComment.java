package com.vaya.comment.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.vaya.member.domain.Member;
import com.vaya.postSmallGroup.domain.PostSmallGroup;
import com.vaya.postTeam.domain.PostTeam;

@Entity
@Table(name="vayacomment")
public class VayaComment {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="body")
	private String body;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="MM/dd/yyyy hh:mm:ss a")
	@Column(name="date")
	private Date date = new Date();
	
	@ManyToOne
	@JoinColumn(name="post_small_group_id")
	private PostSmallGroup postSmallGroup;

	@ManyToOne
	@JoinColumn(name="post_team_id")
	private PostTeam postTeam;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@Column(name="delete_YN")
	private char delete_YN;

	public VayaComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VayaComment(Long id, String body, Date date, PostSmallGroup postSmallGroup, PostTeam postTeam, Member member, char delete_YN) {
		super();
		this.id = id;
		this.body = body;
		this.date = date;
		this.postSmallGroup = postSmallGroup;
		this.postTeam = postTeam;
		this.member = member;
		this.delete_YN = delete_YN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PostSmallGroup getPostSmallGroup() {
		return postSmallGroup;
	}

	public void setPostSmallGroup(PostSmallGroup postSmallGroup) {
		this.postSmallGroup = postSmallGroup;
	}

	public PostTeam getPostTeam() {
		return postTeam;
	}

	public void setPostTeam(PostTeam postTeam) {
		this.postTeam = postTeam;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public char getDelete_YN() {
		return delete_YN;
	}

	public void setDelete_YN(char delete_YN) {
		this.delete_YN = delete_YN;
	}
}
