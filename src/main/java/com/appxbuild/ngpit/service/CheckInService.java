package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.entity.CheckIn;

import java.util.List;

public interface CheckInService {
    List<CheckIn> findAll();
    CheckIn findById(int theId);
    CheckIn save(CheckIn checkIn);
    void deleteById(int theId);
}
