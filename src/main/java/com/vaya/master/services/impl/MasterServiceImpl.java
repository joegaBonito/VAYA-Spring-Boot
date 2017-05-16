package com.vaya.master.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.master.domain.Master;
import com.vaya.master.repositories.MasterRepository;
import com.vaya.master.services.MasterService;

@Service
public class MasterServiceImpl implements MasterService {
	
	private MasterRepository masterRepository;
	
	@Autowired
	public MasterServiceImpl(MasterRepository masterRepository) {
		this.masterRepository = masterRepository;
	}
	
	public List<Master> list() {
		return (List<Master>) masterRepository.findAll();
	}
	
	
}
