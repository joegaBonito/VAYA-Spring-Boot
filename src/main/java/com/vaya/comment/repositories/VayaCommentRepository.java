package com.vaya.comment.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vaya.comment.domain.VayaComment;

@Repository
public interface VayaCommentRepository extends CrudRepository<VayaComment, Long> {
	
	@Query("SELECT c FROM VayaComment c WHERE c.postSmallGroup.id = :#{#id} AND c.delete_YN = 'N' ORDER BY c.id DESC")
	public Page<VayaComment> findPostSmallGroupCommentsByDeleteYNByPagination(@Param("id") Long id, Pageable pageable);

	@Query("SELECT c FROM VayaComment c WHERE c.postTeam.id = :#{#id} AND c.delete_YN = 'N' ORDER BY c.id DESC")
	public Page<VayaComment> findPostTeamCommentsByDeleteYNByPagination(@Param("id") Long id, Pageable pageable);
}
