package com.vaya.retreat.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.retreat.domain.Retreat;
import com.vaya.retreat.repositories.RetreatRepository;
import com.vaya.retreat.services.RetreatService;
@Service
public class RetreatServiceImpl implements RetreatService {
	@Autowired 
	private RetreatRepository retreatRepository;
	
	public RetreatServiceImpl(RetreatRepository retreatRepository) {
		this.retreatRepository = retreatRepository;
	}

	public List<Retreat> retreatList() {
		return retreatRepository.findByOrderByRetreatName();
	}

	public void save(Retreat retreat) {
		retreatRepository.save(retreat);
	}
}
