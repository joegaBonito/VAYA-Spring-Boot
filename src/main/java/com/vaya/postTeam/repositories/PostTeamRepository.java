package com.vaya.postTeam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya.postTeam.domain.PostTeam;

@Repository
public interface PostTeamRepository extends CrudRepository<PostTeam, Long>{
	public List<PostTeam> findAllByOrderByDateDesc();
	
	@Query("SELECT pt FROM PostTeam pt WHERE pt.deleteYN = 'N' ORDER BY pt.date")
	List<PostTeam> findAllByDeleteYNorderbyDateDesc();
	
	@Query("SELECT pt FROM PostTeam pt WHERE pt.member.memberId = :#{#id} AND pt.deleteYN = 'N' ORDER BY pt.date")
	List<PostTeam> findAllByMemberMemberIdOrderByDateDescQuery(@Param("id") Long id);
}
