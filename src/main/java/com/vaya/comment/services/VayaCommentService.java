package com.vaya.comment.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vaya.comment.domain.VayaComment;

public interface VayaCommentService {
	public void saveComment(VayaComment vayaComment);
	public void deleteComment(Long id);
	public Page<VayaComment> commentPostSmallGroupList(Long id, Pageable pageable);
	public Page<VayaComment> commentPostTeamList(Long id, Pageable pageable);
	public VayaComment get(Long id);
	public void addComment(VayaComment vayaComment);
	
}
