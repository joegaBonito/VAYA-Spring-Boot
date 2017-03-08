package com.vaya.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.domain.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	Post findFirstByOrderByPostedOnDesc();

	List<Post> findAllByOrderByPostedOnDesc();

	List<Post> findAllByPostIdOrderByPostedOnDesc(Long id);

	List<Post> findAllByMemberMemberIdOrderByPostedOnDesc(Long id);

}
