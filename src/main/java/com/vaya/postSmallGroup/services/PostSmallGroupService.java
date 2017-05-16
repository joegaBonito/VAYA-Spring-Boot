package com.vaya.postSmallGroup.services;

import java.util.List;

import com.vaya.postSmallGroup.domain.PostSmallGroup;

public interface PostSmallGroupService {
	public List<PostSmallGroup> list();
	public PostSmallGroup get(Long id);
	public void postSave(PostSmallGroup postSmallGroup);
	public void postDelete(Long id);
}
