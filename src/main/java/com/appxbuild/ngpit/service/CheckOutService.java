package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.entity.CheckOut;

import java.util.List;

public interface CheckOutService {

    List<CheckOut> findAll();
    CheckOut findById(int theId);
    CheckOut save(CheckOut checkOut);
    void deleteById(int theId);
}
