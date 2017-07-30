package com.vaya.postTeam.controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.vaya.postTeam.services.PostTeamService;


@Controller
public class AdminPostTeamController {
	private PostTeamService postTeamService;
	
	@Autowired
	public AdminPostTeamController(PostTeamService postTeamService) {
		this.postTeamService = postTeamService;
	}

	
	/*
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value = "/postteams/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
		byte[] imageContent = postTeamService.get(id).getFileData();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
