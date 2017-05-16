package com.vaya.accounting.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.accounting.domain.Accounting;

@Repository
public interface AccountingRepository extends CrudRepository<Accounting, Long> {
	List<Accounting> findByOrderByAccountingId();
}
