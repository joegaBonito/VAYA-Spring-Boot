package com.vaya.general.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.general.domain.Album;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

	@Query("SELECT a FROM Album a")
	List<Album> getPictures();

}
