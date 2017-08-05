package com.vaya.member.services;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaya.member.domain.Member;
import com.vaya.member.repositories.MemberRepository;
import com.vaya.smallgroup.services.impl.SmallGroupServiceImpl;
import com.vaya.team.services.impl.TeamServiceImpl;
@Service
public class MemberService {
	
	private MemberRepository memberRepository;
	private SmallGroupServiceImpl smallGroupServiceImpl;
	private TeamServiceImpl teamServiceImpl;
	
	
	@Autowired
	public MemberService(MemberRepository memberRepository, SmallGroupServiceImpl smallGroupServiceImpl, TeamServiceImpl teamServiceImpl) {
		this.memberRepository = memberRepository;
		this.smallGroupServiceImpl = smallGroupServiceImpl;
		this.teamServiceImpl =teamServiceImpl;
	}
	

	public Page<Member> list(Pageable pageable) {
		return memberRepository.findAllByDeleteYNOrderByEmail(pageable);
	}
	
	public List<Member> listWithoutPagination() {
		return memberRepository.findAllByDeleteYNOrderByEmail();
	}

	public void create(Member member) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setConfirmPassword(bCryptPasswordEncoder.encode(member.getConfirmPassword()));
		//Controlling the role of the user
		member.setRole("GUEST");
		member.setDelete_YN('N');
		member.setSmallGroup(smallGroupServiceImpl.get((long) 1));
		member.setTeam(teamServiceImpl.get((long) 1));
		save(member);
	}
	
	public void save(Member member) {
		memberRepository.save(member);
	}


	public Member get(Long id) {
		return memberRepository.findOne(id);
	}


	public void delete(Long id) {
		Member member = memberRepository.findOne(id);
		member.setDelete_YN('Y');
		save(member);
	}


	public Member getMember(String userName,Pageable pageable) {
		Long tempId = (long) 0;
		for(Member member : list(pageable)){
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
		member.setDelete_YN('N');
		memberRepository.save(member);
	}


	public Long findIdByUsername(String username) {
		Member member = memberRepository.findByEmail(username);
		return member.getMemberId();
	}
	
	public Member findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}


	public Member editUserinfo(Principal principal,Pageable pageable) {
		for(Member member : list(pageable)) {
			if(member.getEmail().equals(principal.getName())) {
				return member;
			}
		}
		return null;
	}
}
