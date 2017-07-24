package com.vaya.team.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.team.domain.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

	List<Team> findByOrderByTeamName();
	
	@Query("SELECT a FROM Team a WHERE a.delete_YN = 'N' ORDER BY a.teamName")
	Page<Team> findAllByDeleteYNOrderByTeamQuery(Pageable pageable);
}
