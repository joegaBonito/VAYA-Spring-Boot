package com.vaya.general.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vaya.general.domain.Gallery;

@Repository
public interface GalleryRepository extends CrudRepository<Gallery, Long> {

	@Query("SELECT g FROM Gallery g")
	List<Gallery> getGalleryPictures();

}
