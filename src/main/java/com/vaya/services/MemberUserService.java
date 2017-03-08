package com.vaya.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaya.domain.Member;
import com.vaya.domain.Role;
import com.vaya.repositories.MemberRepository;

@Service
public class MemberUserService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	public MemberUserService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public MemberUserService() {}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(username);
		if(member != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if (member.getRole()==Role.ADMIN) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			else 
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User(member.getEmail(),member.getPassword(),authorities); 
		} 
		throw new UsernameNotFoundException("User '" + username + "' not found.");
	}
}
