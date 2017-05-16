package com.vaya.postTeam.services;

import java.util.List;

import com.vaya.postTeam.domain.PostTeam;

public interface PostTeamService {
	public List<PostTeam> list();
	public PostTeam get(Long id);
	public void postSave(PostTeam postTeam);
}
