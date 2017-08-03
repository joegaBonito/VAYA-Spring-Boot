package com.vaya.postSmallGroup.domain;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.vaya.comment.domain.VayaComment;
import com.vaya.member.domain.Member;
import com.vaya.smallgroup.domain.SmallGroup;


/*
 * This is a Post entity for Accounting.
 */
@Entity
@Table(name="post_small_group")
public class PostSmallGroup {
	@Id 
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat (pattern="MM/dd/yyyy hh:mm:ss a")
	private Date date = new Date();
	
	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	@Column(name="file_data")
	private byte[] fileData;
	
	@NotEmpty
	@Column(columnDefinition = "TEXT")
	private String body;
	
	@ManyToOne
	@JoinColumn(name="small_group_id")
	private SmallGroup smallGroup;
	
	@Column(name="delete_YN")
	private char deleteYN;
	
	@OneToMany(cascade={CascadeType.ALL}, mappedBy="postSmallGroup")
	private Set<VayaComment> vayaComment;

	public PostSmallGroup() {}
	
	public PostSmallGroup(Long id, String title, Date date, Member member, byte[] fileData, String body, SmallGroup smallGroup, char deleteYN, Set<VayaComment> vayaComment) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.member = member;
		this.fileData = fileData;
		this.body = body;
		this.smallGroup = smallGroup;
		this.deleteYN = deleteYN;
		this.vayaComment = vayaComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public SmallGroup getSmallGroup() {
		return smallGroup;
	}

	public void setSmallGroup(SmallGroup smallGroup) {
		this.smallGroup = smallGroup;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}

	public Set<VayaComment> getComment() {
		return vayaComment;
	}

	public void setComment(Set<VayaComment> vayaComment) {
		this.vayaComment = vayaComment;
	}
}


