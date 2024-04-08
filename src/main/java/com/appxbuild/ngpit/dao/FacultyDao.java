package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyDao extends JpaRepository<Faculty, Integer> {
}
