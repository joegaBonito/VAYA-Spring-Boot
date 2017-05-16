package com.vaya.postTeam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.postTeam.domain.PostTeam;

@Repository
public interface PostTeamRepository extends CrudRepository<PostTeam, Long>{
	public List<PostTeam> findAllByOrderByDateDesc();
}
