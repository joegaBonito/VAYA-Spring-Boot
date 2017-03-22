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
@Table(name="Etc")
public class Etc {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long etcId;
	
	@Column(name="name")
	private String etcName;
	
	@ManyToOne
	@JoinColumn(name="accounting_id")
	private Accounting accounting;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="etc")
	private Set<Post> post;

	public Etc(Long etcId, String etcName, Accounting accounting, Set<Post> post) {
		super();
		this.etcId = etcId;
		this.etcName = etcName;
		this.accounting = accounting;
		this.post = post;
	}
	
	public Etc() {}

	public Long getEtcId() {
		return etcId;
	}

	public void setEtcId(Long etcId) {
		this.etcId = etcId;
	}

	public String getEtcName() {
		return etcName;
	}

	public void setEtcName(String etcName) {
		this.etcName = etcName;
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
