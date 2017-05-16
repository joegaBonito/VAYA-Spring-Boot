package com.vaya.postAccounting.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.postAccounting.domain.PostAccounting;

@Repository
public interface PostAccountingRepository extends CrudRepository<PostAccounting, Long> {
	PostAccounting findFirstByOrderByPostedOnDesc();

	List<PostAccounting> findAllByOrderByPostedOnDesc();

	List<PostAccounting> findAllByPostIdOrderByPostedOnDesc(Long id);

	List<PostAccounting> findAllByMemberMemberIdOrderByPostedOnDesc(Long id);

}
