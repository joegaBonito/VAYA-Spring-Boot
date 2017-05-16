package com.vaya.team.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.team.domain.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

	List<Team> findByOrderByTeamName();
	
}
