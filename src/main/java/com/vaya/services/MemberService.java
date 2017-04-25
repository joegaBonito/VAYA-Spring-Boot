package com.vaya.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaya.domain.Member;
import com.vaya.repositories.MemberRepository;
@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	

	public List<Member> list() {
		return memberRepository.findAllByOrderByEmail();
	}

	public Member save(Member member) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setConfirmPassword(bCryptPasswordEncoder.encode(member.getConfirmPassword()));
		//Controlling the role of the user
		member.setRole("GUEST");
		return memberRepository.save(member);
	}


	public Member get(Long id) {
		return memberRepository.findOne(id);
	}


	public void delete(Long id) {
		memberRepository.delete(id);
	}


	public Member getMember(String userName) {
		Long tempId = (long) 0;
		for(Member member : list()){
			if(member.getEmail().equals(userName))
				tempId = member.getMemberId();
		}
		return get(tempId);
	}
	
	public List<String> roles() {
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		roles.add("USER");
		roles.add("GUEST");
		return roles;
	}
	
	public void editSave(Member member) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setConfirmPassword(bCryptPasswordEncoder.encode(member.getConfirmPassword()));
		member.setRole(member.getRole());
		memberRepository.save(member);
	}


	public Long findIdByUsername(String username) {
		Member member = memberRepository.findByEmail(username);
		return member.getMemberId();
	}
}
