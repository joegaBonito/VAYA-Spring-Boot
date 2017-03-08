package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Retreat;
import com.vaya.repositories.RetreatRepository;
@Service
public class RetreatService {
	@Autowired 
	private RetreatRepository retreatRepository;
	
	public RetreatService(RetreatRepository retreatRepository) {
		this.retreatRepository = retreatRepository;
	}

	public List<Retreat> retreatList() {
		return retreatRepository.findByOrderByRetreatName();
	}

	public void save(Retreat retreat) {
		retreatRepository.save(retreat);
	}
}
