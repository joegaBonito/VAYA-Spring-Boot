package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Master;
import com.vaya.repositories.MasterRepository;

@Service
public class MasterService {
	
	private MasterRepository masterRepository;
	
	@Autowired
	public MasterService(MasterRepository masterRepository) {
		this.masterRepository = masterRepository;
	}
	
	public List<Master> list() {
		return (List<Master>) masterRepository.findAll();
	}
	
	
}
