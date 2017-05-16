package com.vaya.retreat.domain;

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
@Table(name="Retreat")
public class Retreat {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long retreatId;
	
	@Column(name="name")
	private String retreatName;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(nullable=true, name="accounting_id")
	private Accounting accounting;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="retreat")
	private Set<PostAccounting> postAccounting;
	

	@OneToOne(cascade={CascadeType.ALL},mappedBy="retreat")
	private Master masterId;


	public Retreat() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Retreat(Long retreatId, String retreatName, Accounting accounting, Set<PostAccounting> postAccounting, Master masterId) {
		super();
		this.retreatId = retreatId;
		this.retreatName = retreatName;
		this.accounting = accounting;
		this.postAccounting = postAccounting;
		this.masterId = masterId;
	}


	public Long getRetreatId() {
		return retreatId;
	}


	public void setRetreatId(Long retreatId) {
		this.retreatId = retreatId;
	}


	public String getRetreatName() {
		return retreatName;
	}


	public void setRetreatName(String retreatName) {
		this.retreatName = retreatName;
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
