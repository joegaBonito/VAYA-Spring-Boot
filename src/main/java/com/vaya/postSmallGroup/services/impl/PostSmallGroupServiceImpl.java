package com.vaya.postSmallGroup.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.vaya.postSmallGroup.domain.PostSmallGroup;
import com.vaya.postSmallGroup.repositories.PostSmallGroupRepository;
import com.vaya.postSmallGroup.services.PostSmallGroupService;

@Service
public class PostSmallGroupServiceImpl implements PostSmallGroupService {
	
	PostSmallGroupRepository postSmallGroupRepository;
	
	@Autowired
	public PostSmallGroupServiceImpl(PostSmallGroupRepository postSmallGroupRepository) {
		this.postSmallGroupRepository = postSmallGroupRepository;
	}
	
	public Page<PostSmallGroup> list(Long id, Pageable pageable) {
		return postSmallGroupRepository.findAllBySmallGroupIdOrderbyIdDescQuery(id, pageable);
	}

	public PostSmallGroup get(Long id) {
		return postSmallGroupRepository.findOne(id);
	}

	public void postSave(PostSmallGroup postSmallGroup) {
		postSmallGroupRepository.save(postSmallGroup); 
	}
	
	public void postDelete(Long id) {
		PostSmallGroup postSmallGroup = postSmallGroupRepository.findOne(id);
		postSmallGroup.setDeleteYN('Y');
		postSave(postSmallGroup);
	}

	public Page<PostSmallGroup> listByMember(Long id,Pageable pageable) {
		return postSmallGroupRepository.findAllByMemberMemberIdOrderByIdDescQuery(id,pageable);
	}

}
