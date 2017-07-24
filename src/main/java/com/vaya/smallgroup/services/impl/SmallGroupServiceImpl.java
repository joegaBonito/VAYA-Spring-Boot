package com.vaya.smallgroup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Override
	public Page<SmallGroup> list(Pageable pageable) {
		return smallGroupRepository.findAllByDeleteYNOrderBySmallGroupName(pageable);
	}
	@Override
	public void save(SmallGroup smallGroup) {
		smallGroupRepository.save(smallGroup);
	}
	@Override
	public SmallGroup get(Long id) {
		return smallGroupRepository.findOne(id);
	}
	@Override
	public void delete(Long id) {
		SmallGroup smallGroup = smallGroupRepository.findOne(id);
		smallGroup.setDelete_YN('Y');
		save(smallGroup);
	}
}
