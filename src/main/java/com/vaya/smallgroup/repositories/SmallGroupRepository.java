package com.vaya.smallgroup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.smallgroup.domain.SmallGroup;

@Repository
public interface SmallGroupRepository extends CrudRepository<SmallGroup, Long> {

	List<SmallGroup> findByOrderByName();

}
