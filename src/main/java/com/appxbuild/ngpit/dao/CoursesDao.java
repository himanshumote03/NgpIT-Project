package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesDao extends JpaRepository<Courses, Integer> {
}
