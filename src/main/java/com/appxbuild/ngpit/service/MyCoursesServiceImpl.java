package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.dao.MyCoursesDao;
import com.appxbuild.ngpit.entity.MyCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyCoursesServiceImpl implements MyCoursesService {

    private MyCoursesDao myCoursesDao;

    @Autowired
    public MyCoursesServiceImpl(MyCoursesDao myCoursesDao) {
        this.myCoursesDao = myCoursesDao;
    }

    @Override
    public List<MyCourses> findALl() {
        return myCoursesDao.findAll();
    }

    @Override
    public MyCourses findById(int theId) {
        Optional<MyCourses> result = myCoursesDao.findById(theId);
        MyCourses myCourses = null;
        if(result.isPresent()){
            myCourses = result.get();
        }
        else {
            throw new RuntimeException("My Course id is not found " + theId);
        }
        return myCourses;
    }

    @Override
    public MyCourses save(MyCourses myCourses) {
        return myCoursesDao.save(myCourses);
    }

    @Override
    public void deleteById(int theId) {
        myCoursesDao.deleteById(theId);
    }
}
