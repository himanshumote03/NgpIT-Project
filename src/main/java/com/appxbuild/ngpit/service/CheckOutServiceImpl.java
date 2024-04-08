package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.dao.CheckOutDao;
import com.appxbuild.ngpit.entity.CheckOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    private CheckOutDao checkOutDao;

    @Autowired
    public CheckOutServiceImpl(CheckOutDao checkOutDao) {
        this.checkOutDao = checkOutDao;
    }

    @Override
    public List<CheckOut> findAll() {
        return checkOutDao.findAll();
    }

    @Override
    public CheckOut findById(int theId) {
        Optional<CheckOut> res = checkOutDao.findById(theId);
        CheckOut checkOut = null;
        if (res.isPresent()) {
            checkOut = res.get();
        }
        else {
            throw new RuntimeException("CheckOut id is not found" + theId);
        }
        return checkOut;
    }

    @Override
    public CheckOut save(CheckOut checkOut) {
        return checkOutDao.save(checkOut);
    }

    @Override
    public void deleteById(int theId) {
        checkOutDao.deleteById(theId);
    }
}
