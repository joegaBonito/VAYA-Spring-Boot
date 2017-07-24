package com.vaya.team.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaya.team.domain.Team;
import com.vaya.team.repositories.TeamRepository;
import com.vaya.team.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	private TeamRepository teamRepository;
	
	@Autowired 
	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}
	@Override
	public Page<Team> teamList(Pageable pageable) {
		return teamRepository.findAllByDeleteYNOrderByTeamQuery(pageable);
	}
	@Override
	public void save(Team team) {
		teamRepository.save(team);
	}
	@Override
	public Team get(Long id) {
		return teamRepository.findOne(id);
	}
	@Override
	public void delete(Long id) {
		Team team = teamRepository.findOne(id);
		team.setDelete_YN('Y');
		save(team);
	}
}
