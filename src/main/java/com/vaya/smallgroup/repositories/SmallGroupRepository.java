package com.vaya.smallgroup.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.smallgroup.domain.SmallGroup;

@Repository
public interface SmallGroupRepository extends CrudRepository<SmallGroup, Long> {

	List<SmallGroup> findByOrderByName();

	@Query("SELECT sg FROM SmallGroup sg WHERE sg.delete_YN = 'N' ORDER BY sg.id")
	Page<SmallGroup> findAllByDeleteYNOrderBySmallGroupId(Pageable pageable);
}
