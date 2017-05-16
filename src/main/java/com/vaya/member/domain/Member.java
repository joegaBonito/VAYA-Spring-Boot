package com.vaya.member.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.vaya.postAccounting.domain.PostAccounting;
import com.vaya.postSmallGroup.domain.PostSmallGroup;

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

	public Member() {}

	public Member(Long memberId, String name, String email, String password, String confirmPassword, String role,
			Set<PostAccounting> postAccounting, Set<PostSmallGroup> postSmallGroup) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
		this.postAccounting = postAccounting;
		this.postSmallGroup = postSmallGroup;
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

	public void setPost(Set<PostAccounting> postAccounting) {
		this.postAccounting = postAccounting;
	}

	public Set<PostSmallGroup> getPostSmallGroup() {
		return postSmallGroup;
	}

	public void setPostSmallGroup(Set<PostSmallGroup> postSmallGroup) {
		this.postSmallGroup = postSmallGroup;
	}
}
