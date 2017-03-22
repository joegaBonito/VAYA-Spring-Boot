package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Team;
import com.vaya.repositories.TeamRepository;

@Service
public class TeamService {
	@Autowired 
	private TeamRepository teamRepository;
	
	public TeamService(TeamRepository teamRepository) {
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
