package com.vaya.smallgroup.services;

import java.util.List;

import com.vaya.smallgroup.domain.SmallGroup;

public interface SmallGroupService {
	public List<SmallGroup> list();
	public void save(SmallGroup smallGroup);
	public SmallGroup get(Long id);
}
