package com.vaya.master.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.master.domain.Master;

@Repository
public interface MasterRepository extends CrudRepository<Master,Long> {
	
}
