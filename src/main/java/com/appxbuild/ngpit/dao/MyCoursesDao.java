package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.MyCourses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCoursesDao extends JpaRepository<MyCourses, Integer> {
}
