package com.vaya.comment.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaya.comment.domain.VayaComment;
import com.vaya.comment.repositories.VayaCommentRepository;
import com.vaya.comment.services.VayaCommentService;

@Service
public class VayaCommentServiceImpl implements VayaCommentService {
	
	private VayaCommentRepository vayaCommentRepository;
	
	@Autowired
	public VayaCommentServiceImpl(VayaCommentRepository vayaCommentRepository) {
		this.vayaCommentRepository = vayaCommentRepository;
	}
	
	@Override
	public void addComment(VayaComment vayaComment) {
		vayaComment.setDelete_YN('N');
		saveComment(vayaComment);
	}

	@Override
	public void saveComment(VayaComment vayaComment) {
		vayaCommentRepository.save(vayaComment);
	}

	@Override
	public void deleteComment(Long id) {
		VayaComment vayaComment = vayaCommentRepository.findOne(id);
		vayaComment.setDelete_YN('Y');
		saveComment(vayaComment);
	}

	@Override
	public Page<VayaComment> commentPostSmallGroupList(Long id, Pageable pageable) {
		return vayaCommentRepository.findPostSmallGroupCommentsByDeleteYNByPagination(id,pageable);
	}
	
	@Override
	public Page<VayaComment> commentPostTeamList(Long id, Pageable pageable) {
		return vayaCommentRepository.findPostTeamCommentsByDeleteYNByPagination(id,pageable);
	}

	@Override
	public VayaComment get(Long id) {
		return vayaCommentRepository.findOne(id);
	}



}
