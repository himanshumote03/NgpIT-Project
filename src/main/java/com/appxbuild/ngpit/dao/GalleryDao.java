package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryDao extends JpaRepository<Gallery, Integer> {
}
