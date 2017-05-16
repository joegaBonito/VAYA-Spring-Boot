package com.vaya.team.services;

import java.util.List;

import com.vaya.team.domain.Team;

public interface TeamService {
	public List<Team> teamList();
	public void save(Team team);
	public Team get(Long id);
}
