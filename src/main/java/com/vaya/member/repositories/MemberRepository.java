package com.vaya.member.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vaya.member.domain.Member;

public interface MemberRepository extends CrudRepository<Member,Long> {
	public Member findFirstByOrderByEmail();
	public Set<Member> findAllByOrderByEmail();
	@Query("SELECT m FROM Member m WHERE m.delete_YN = 'N' ORDER BY m.email")
	public Page<Member> findAllByDeleteYNOrderByEmail(Pageable pageable);
	@Query("SELECT m FROM Member m WHERE m.delete_YN = 'N' ORDER BY m.email")
	public List<Member> findAllByDeleteYNOrderByEmail();
	public Member findByEmail(String email);
	
	
}
