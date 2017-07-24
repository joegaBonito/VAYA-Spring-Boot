package com.vaya.smallgroup.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vaya.member.domain.Member;
import com.vaya.postSmallGroup.domain.PostSmallGroup;

@Entity
@Table(name="small_group")
public class SmallGroup {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;

	@OneToMany(cascade={CascadeType.ALL}, mappedBy = "smallGroup")
	private List<PostSmallGroup> postSmallGroup;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="smallGroup")
	private List<Member> member;
	
	@Column(name="delete_YN") 
	private char delete_YN;
	
	@Column(name="semester")
	private String semester;

	public SmallGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SmallGroup(Long id, String name, List<PostSmallGroup> postSmallGroup, List<Member> member, char delete_YN,
			String semester) {
		super();
		this.id = id;
		this.name = name;
		this.postSmallGroup = postSmallGroup;
		this.member = member;
		this.delete_YN = delete_YN;
		this.semester = semester;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PostSmallGroup> getPostSmallGroup() {
		return postSmallGroup;
	}

	public void setPostSmallGroup(List<PostSmallGroup> postSmallGroup) {
		this.postSmallGroup = postSmallGroup;
	}

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

	public char getDelete_YN() {
		return delete_YN;
	}

	public void setDelete_YN(char delete_YN) {
		this.delete_YN = delete_YN;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
}
