package com.vaya.general.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gallery")
public class Gallery {

	@Id
	@GeneratedValue
	private Long Id;
	
	@Column(name="file_data")
	private  byte[] fileData;

	public Gallery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gallery(Long id, byte[] fileData) {
		super();
		Id = id;
		this.fileData = fileData;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
}
