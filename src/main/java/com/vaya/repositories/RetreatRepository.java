package com.vaya.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.domain.Retreat;

@Repository
public interface RetreatRepository extends CrudRepository<Retreat, Long> {
	List<Retreat> findByOrderByRetreatName();
}
