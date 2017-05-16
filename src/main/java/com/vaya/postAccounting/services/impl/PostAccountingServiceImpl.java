package com.vaya.postAccounting.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.postAccounting.domain.PostAccounting;
import com.vaya.postAccounting.repositories.PostAccountingRepository;
import com.vaya.postAccounting.services.PostAccountingService;

@Service
public class PostAccountingServiceImpl implements PostAccountingService {
	
	@Autowired
	PostAccountingRepository postAccountingRepository;
	
	public PostAccountingServiceImpl(PostAccountingRepository postAccountingRepository) {
		this.postAccountingRepository = postAccountingRepository;
	}

	public List<PostAccounting> list() {
		return postAccountingRepository.findAllByOrderByPostedOnDesc();
	}

	public List<PostAccounting> listByMember(Long id) {
			return postAccountingRepository.findAllByMemberMemberIdOrderByPostedOnDesc(id);
	}

	public PostAccounting save(PostAccounting post) { 
		return postAccountingRepository.save(post);
	}

	public PostAccounting get(Long id) {
		return postAccountingRepository.findOne(id);
	}

	public void delete(Long id) {
		postAccountingRepository.delete(id);
	}
}

