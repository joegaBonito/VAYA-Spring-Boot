package com.vaya.postTeam.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya.postTeam.domain.PostTeam;

@Repository
public interface PostTeamRepository extends CrudRepository<PostTeam, Long>{
	public List<PostTeam> findAllByOrderByDateDesc();
	
	@Query("SELECT pt FROM PostTeam pt WHERE pt.team.id = :#{#id} AND pt.deleteYN = 'N' ORDER BY pt.id DESC")
	Page<PostTeam> findAllBySmallGroupIdOrderbyIdDescQuery(@Param("id") Long id, Pageable pageable);
	
	@Query("SELECT pt FROM PostTeam pt WHERE pt.member.memberId = :#{#id} AND pt.deleteYN = 'N' ORDER BY pt.id DESC")
	Page<PostTeam> findAllByMemberMemberIdOrderByIdDescQuery(@Param("id") Long id, Pageable pageable);
}
