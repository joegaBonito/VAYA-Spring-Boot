package com.vaya.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vaya.domain.Member;

public interface MemberRepository extends CrudRepository<Member,Long> {
	Member findFirstByOrderByEmail();
	List<Member> findAllByOrderByEmail();
	Member findByEmail(String email);
}
