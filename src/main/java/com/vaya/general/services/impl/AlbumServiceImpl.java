package com.vaya.general.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya.general.domain.Album;
import com.vaya.general.repositories.AlbumRepository;
import com.vaya.general.services.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {
	
	private AlbumRepository albumRepository;
	
	@Autowired
	public AlbumServiceImpl(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}
	
	public List<Album> getPictures() {
		return albumRepository.getPictures();
	}

	public Album get(Long id) {
		return albumRepository.findOne(id);
	};
}
