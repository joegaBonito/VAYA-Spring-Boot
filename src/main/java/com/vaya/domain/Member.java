package com.vaya.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
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
	private String email;
	
	@NotNull(message="Password is Required")
	@Column(name="password")
	private String password;
	
	@NotNull(message="Confirming Password is Required")
	@Column(name="confirm_password")
	private String confirmPassword;

	@Enumerated(EnumType.STRING)
	private Role role; 
	
	@OneToMany(mappedBy="member")
	private Set<Post> post;

	public Member() {}

	public Member(Long memberId, String name, String email, String password, String confirmPassword, Role role,
			Set<Post> post) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
		this.post = post;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}
}
