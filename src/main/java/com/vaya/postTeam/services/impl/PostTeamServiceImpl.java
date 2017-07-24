package com.vaya.postTeam.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaya.postTeam.domain.PostTeam;
import com.vaya.postTeam.repositories.PostTeamRepository;
import com.vaya.postTeam.services.PostTeamService;

@Service
public class PostTeamServiceImpl implements PostTeamService {
	
	PostTeamRepository postTeamRepository;
	
	@Autowired
	public PostTeamServiceImpl(PostTeamRepository postTeamRepository) {
		this.postTeamRepository = postTeamRepository;
	}
	
	public Page<PostTeam> list(Long id, Pageable pageable) {
		return postTeamRepository.findAllBySmallGroupIdOrderbyIdDescQuery(id, pageable);
	}

	public PostTeam get(Long id) {
		return postTeamRepository.findOne(id);
	}

	public void postSave(PostTeam postTeam) {
		postTeamRepository.save(postTeam); 
	}

	public void postDelete(Long id) {
		PostTeam postTeam = postTeamRepository.findOne(id);
		postTeam.setDeleteYN('Y');
		postSave(postTeam);
		
	}

	public Page<PostTeam> listByMember(Long id, Pageable pageable) {
		return postTeamRepository.findAllByMemberMemberIdOrderByIdDescQuery(id,pageable);
	}
}
