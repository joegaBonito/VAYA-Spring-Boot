package com.vaya.postSmallGroup.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vaya.postSmallGroup.domain.PostSmallGroup;

public interface PostSmallGroupService {
	public Page<PostSmallGroup> list(Long id, Pageable pageable);
	public PostSmallGroup get(Long id);
	public void postSave(PostSmallGroup postSmallGroup);
	public void postDelete(Long id);
	public Page<PostSmallGroup> listByMember(Long id,Pageable pageable);
}
