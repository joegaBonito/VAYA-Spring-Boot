package com.vaya.postSmallGroup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya.postSmallGroup.domain.PostSmallGroup;

@Repository
public interface PostSmallGroupRepository extends CrudRepository<PostSmallGroup, Long> {
	List<PostSmallGroup> findAllByOrderByDateDesc();
	
	@Query("SELECT smg FROM PostSmallGroup smg WHERE smg.deleteYN = 'N' ORDER BY smg.date")
	List<PostSmallGroup> findAllByDeleteYNorderbyDateDesc();

	@Query("SELECT smg FROM PostSmallGroup smg WHERE smg.member.memberId = :#{#id} AND smg.deleteYN = 'N' ORDER BY smg.date")
	List<PostSmallGroup> findAllByMemberMemberIdOrderByDateDescQuery(@Param("id") Long id);
}
