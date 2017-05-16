package com.vaya.team.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.team.domain.Team;
import com.vaya.team.repositories.TeamRepository;
import com.vaya.team.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	@Autowired 
	private TeamRepository teamRepository;
	
	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public List<Team> teamList() {
		return teamRepository.findByOrderByTeamName();
	}

	public void save(Team team) {
		teamRepository.save(team);
	}
	
	public Team get(Long id) {
		return teamRepository.findOne(id);
	}
	
}
