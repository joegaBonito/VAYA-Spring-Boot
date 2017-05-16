package com.vaya.retreat.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.retreat.domain.Retreat;

@Repository
public interface RetreatRepository extends CrudRepository<Retreat, Long> {
	List<Retreat> findByOrderByRetreatName();
}
