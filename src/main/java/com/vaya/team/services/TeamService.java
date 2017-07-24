package com.vaya.team.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vaya.team.domain.Team;

public interface TeamService {
	public Page<Team> teamList(Pageable pageable);
	public void save(Team team);
	public Team get(Long id);
	public void delete(Long id);
}
