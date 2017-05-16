package com.vaya.etc.domain;

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
	private Set<PostAccounting> postAccounting;
	
	@OneToOne(cascade={CascadeType.ALL},mappedBy="etc")
	private Master masterId;

	public Etc() {};
	
	public Etc(Long etcId, String etcName, Accounting accounting, Set<PostAccounting> postAccounting, Master masterId) {
		super();
		this.etcId = etcId;
		this.etcName = etcName;
		this.accounting = accounting;
		this.postAccounting = postAccounting;
		this.masterId = masterId;
	}

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
