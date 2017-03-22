package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Etc;
import com.vaya.repositories.EtcRepository;
@Service
public class EtcService {
	@Autowired
	private EtcRepository etcRepository;
	
	public EtcService(EtcRepository etcRepository) {
		this.etcRepository = etcRepository;
	}

	public List<Etc> etcList() {
		return etcRepository.findByOrderByEtcName();
	}

	public void save(Etc etc) {
		etcRepository.save(etc);
	}
}
