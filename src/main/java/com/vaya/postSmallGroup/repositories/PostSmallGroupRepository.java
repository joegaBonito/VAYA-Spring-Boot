package com.vaya.postSmallGroup.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya.postSmallGroup.domain.PostSmallGroup;

@Repository
public interface PostSmallGroupRepository extends CrudRepository<PostSmallGroup, Long> {
	List<PostSmallGroup> findAllByOrderByDateDesc();
	
	@Query("SELECT smg FROM PostSmallGroup smg WHERE smg.smallGroup.id = :#{#id} AND smg.deleteYN = 'N' ORDER BY smg.id DESC")
	Page<PostSmallGroup> findAllBySmallGroupIdOrderbyIdDescQuery(@Param("id") Long id, Pageable pageable);

	@Query("SELECT smg FROM PostSmallGroup smg WHERE smg.member.memberId = :#{#id} AND smg.deleteYN = 'N' ORDER BY smg.id DESC")
	Page<PostSmallGroup> findAllByMemberMemberIdOrderByIdDescQuery(@Param("id") Long id, Pageable pageable);
}
