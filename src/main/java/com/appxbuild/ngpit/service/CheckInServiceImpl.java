package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.dao.CheckInDao;
import com.appxbuild.ngpit.entity.CheckIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckInServiceImpl implements CheckInService {

    private CheckInDao checkInDao;

    @Autowired
    public CheckInServiceImpl(CheckInDao checkInDao) {
        this.checkInDao = checkInDao;
    }

    @Override
    public List<CheckIn> findAll() {
        return checkInDao.findAll();
    }

    @Override
    public CheckIn findById(int theId) {
        Optional<CheckIn> res = checkInDao.findById(theId);
        CheckIn checkIn = null;
        if (res.isPresent()) {
            checkIn = res.get();
        } else {
            throw new RuntimeException("CheckIn id is not found");
        }
        return checkIn;
    }

    @Override
    public CheckIn save(CheckIn checkIn) {
        return checkInDao.save(checkIn);
    }

    @Override
    public void deleteById(int theId) {
        checkInDao.deleteById(theId);
    }
}
