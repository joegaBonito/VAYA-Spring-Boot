package com.vaya.etc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.etc.domain.Etc;
import com.vaya.etc.repositories.EtcRepository;
import com.vaya.etc.services.EtcService;
@Service
public class EtcServiceImpl implements EtcService {
	@Autowired
	private EtcRepository etcRepository;
	
	public EtcServiceImpl(EtcRepository etcRepository) {
		this.etcRepository = etcRepository;
	}

	public List<Etc> etcList() {
		return etcRepository.findByOrderByEtcName();
	}

	public void save(Etc etc) {
		etcRepository.save(etc);
	}
}
