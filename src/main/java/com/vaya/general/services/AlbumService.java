package com.vaya.general.services;

import java.util.List;

import com.vaya.general.domain.Album;

public interface AlbumService {
	public List<Album> getPictures();
	public Album get(Long id);
}
