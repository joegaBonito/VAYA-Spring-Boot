package com.vaya.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.domain.Post;
import com.vaya.repositories.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> list() {
		return postRepository.findAllByOrderByPostedOnDesc();
	}

	public List<Post> listByMember(Long id) {
			return postRepository.findAllByMemberMemberIdOrderByPostedOnDesc(id);
	}

	public Post save(Post post) { 
		return postRepository.save(post);
	}

	public Post get(Long id) {
		return postRepository.findOne(id);
	}

	public void delete(Long id) {
		postRepository.delete(id);
	}
}

