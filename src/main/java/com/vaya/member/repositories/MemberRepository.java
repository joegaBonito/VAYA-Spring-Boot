package com.vaya.member.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.vaya.member.domain.Member;

public interface MemberRepository extends CrudRepository<Member,Long> {
	Member findFirstByOrderByEmail();
	Set<Member> findAllByOrderByEmail();
	Member findByEmail(String email);
}
