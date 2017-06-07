package com.vaya.general.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.general.domain.Gallery;
import com.vaya.general.repositories.GalleryRepository;
import com.vaya.general.services.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService {
	
	private GalleryRepository galleryRepository;
	
	@Autowired
	public GalleryServiceImpl(GalleryRepository galleryRepository) {
		this.galleryRepository = galleryRepository;
	}
	
	public List<Gallery> getGalleryPictures() {
		return galleryRepository.getGalleryPictures();
	}

	public Gallery get(Long id) {
		return galleryRepository.findOne(id);
	};
}
