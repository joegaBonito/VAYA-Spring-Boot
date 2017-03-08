package com.vaya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vaya.services.PostService;

@Controller
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@RequestMapping("/list")
	public String postList(Model model) {
		model.addAttribute("posts",postService.list());
		return "/posts/list";
	}
	
	@RequestMapping("/byMember/{id}")
	public String byAuthor(@PathVariable(value="id") Long id, Model model){
		model.addAttribute("posts", postService.listByMember(id));
		return "posts/list";
	}
	
	@RequestMapping("/view/{id}")
	public String view(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("post",postService.get(id));
		return "posts/view";
	}
}
