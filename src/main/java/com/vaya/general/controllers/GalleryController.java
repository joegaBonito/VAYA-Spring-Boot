package com.vaya.general.controllers;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.vaya.general.services.impl.GalleryServiceImpl;

@Controller
public class GalleryController {
	
	private GalleryServiceImpl galleryServiceImpl;
	
	@Autowired
	public GalleryController(GalleryServiceImpl galleryServiceImpl) {
		this.galleryServiceImpl = galleryServiceImpl;	
	}
	
	@RequestMapping("/gallery")
	public String gallery(Model model){
		model.addAttribute("pictures",galleryServiceImpl.getGalleryPictures());
		return "/gallery/gallery";
	}
	
	/*
	 * Displays image to web from the database blob.
	 */
	@RequestMapping(value = "/gallery/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException {
		byte[] imageContent = galleryServiceImpl.get(id).getFileData();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}
