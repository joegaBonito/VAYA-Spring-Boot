package com.vaya.smallgroup.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.smallgroup.domain.SmallGroup;
import com.vaya.smallgroup.repositories.SmallGroupRepository;
import com.vaya.smallgroup.services.SmallGroupService;

@Service
public class SmallGroupServiceImpl implements SmallGroupService {
	
	private SmallGroupRepository smallGroupRepository;
	
	@Autowired
	public SmallGroupServiceImpl(SmallGroupRepository smallGroupRepository) {
		this.smallGroupRepository = smallGroupRepository;
	}

	public List<SmallGroup> list() {
		return smallGroupRepository.findByOrderByName();
	}

	public void save(SmallGroup smallGroup) {
		smallGroupRepository.save(smallGroup);
	}
	
	public SmallGroup get(Long id) {
		return smallGroupRepository.findOne(id);
	}
}
