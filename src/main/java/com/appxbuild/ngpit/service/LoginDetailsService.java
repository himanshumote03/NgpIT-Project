package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.entity.LoginDetails;

import java.util.List;

public interface LoginDetailsService {
    List<LoginDetails> findAll();
    LoginDetails findById(int theId);
    LoginDetails save(LoginDetails loginDetails);
    void deleteById(int theId);

    LoginDetails findByEmail(String email);
}
