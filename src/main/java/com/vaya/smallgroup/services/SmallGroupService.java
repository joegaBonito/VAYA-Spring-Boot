package com.vaya.smallgroup.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vaya.smallgroup.domain.SmallGroup;

public interface SmallGroupService {
	public Page<SmallGroup> list(Pageable pageable);
	public void save(SmallGroup smallGroup);
	public SmallGroup get(Long id);
	public void delete(Long id);
}
