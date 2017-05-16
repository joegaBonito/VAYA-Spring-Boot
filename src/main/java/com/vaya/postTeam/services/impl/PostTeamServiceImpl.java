package com.vaya.postTeam.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<PostTeam> list() {
		return postTeamRepository.findAllByOrderByDateDesc();
	}

	public PostTeam get(Long id) {
		return postTeamRepository.findOne(id);
	}

	public void postSave(PostTeam postTeam) {
		postTeamRepository.save(postTeam); 
	}
}
