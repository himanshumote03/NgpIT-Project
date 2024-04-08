package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.entity.MyCourses;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyCoursesService {
    List<MyCourses> findALl();
    MyCourses findById(int theId);
    MyCourses save(MyCourses myCourses);
    void deleteById(int theId);
}
