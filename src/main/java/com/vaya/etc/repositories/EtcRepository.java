package com.vaya.etc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.etc.domain.Etc;

@Repository
public interface EtcRepository extends CrudRepository<Etc, Long> {
	List<Etc> findByOrderByEtcName();
}
