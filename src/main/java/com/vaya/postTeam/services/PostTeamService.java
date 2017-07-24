package com.vaya.postTeam.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vaya.postTeam.domain.PostTeam;

public interface PostTeamService {
	public Page<PostTeam> list(Long id,Pageable pageable);
	public PostTeam get(Long id);
	public void postSave(PostTeam postTeam);
	public void postDelete(Long id);
	public Page<PostTeam> listByMember(Long id, Pageable pageable);
}
