package com.vaya.member.domain;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vaya.postAccounting.domain.PostAccounting;
import com.vaya.postSmallGroup.domain.PostSmallGroup;
import com.vaya.postTeam.domain.PostTeam;
import com.vaya.smallgroup.domain.SmallGroup;
import com.vaya.team.domain.Team;

@Entity
@Table(name="member")
public class Member {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long memberId;
	
	@NotNull(message="Name is Required")
	@Column(name="name")
	private String name;
	
	@NotNull(message="Email is Required")
	@Column(name="email")
	@Size(min = 1, max = 100)
	//@Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$")
	private String email;
	
	@NotNull(message="Password is Required")
	@Column(name="password")
	private String password;
	
	@NotNull(message="Confirming Password is Required")
	@Column(name="confirm_password")
	private String confirmPassword;

	@NotNull(message="Role is Required")
	@Column(name="role")
	private String role; 
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="member")
	private Set<PostAccounting> postAccounting;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="member")
	private Set<PostSmallGroup> postSmallGroup;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="member")
	private Set<PostTeam> postTeam;
	
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team; 
	
	@ManyToOne
	@JoinColumn(name="small_group_id")
	private SmallGroup smallGroup; 

	public Member() {}

	public Member(Long memberId, String name, String email, String password, String confirmPassword, String role,
			Set<PostAccounting> postAccounting, Set<PostSmallGroup> postSmallGroup, Set<PostTeam> postTeam, Team team,
			SmallGroup smallGroup) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
		this.postAccounting = postAccounting;
		this.postSmallGroup = postSmallGroup;
		this.postTeam = postTeam;
		this.team = team;
		this.smallGroup = smallGroup;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<PostAccounting> getPostAccounting() {
		return postAccounting;
	}

	public void setPostAccounting(Set<PostAccounting> postAccounting) {
		this.postAccounting = postAccounting;
	}

	public Set<PostSmallGroup> getPostSmallGroup() {
		return postSmallGroup;
	}

	public void setPostSmallGroup(Set<PostSmallGroup> postSmallGroup) {
		this.postSmallGroup = postSmallGroup;
	}

	public Set<PostTeam> getPostTeam() {
		return postTeam;
	}

	public void setPostTeam(Set<PostTeam> postTeam) {
		this.postTeam = postTeam;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public SmallGroup getSmallGroup() {
		return smallGroup;
	}

	public void setSmallGroup(SmallGroup smallGroup) {
		this.smallGroup = smallGroup;
	}
}
