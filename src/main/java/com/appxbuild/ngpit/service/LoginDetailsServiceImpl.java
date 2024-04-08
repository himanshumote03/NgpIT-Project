package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.dao.LoginDetailsDao;
import com.appxbuild.ngpit.entity.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginDetailsServiceImpl implements LoginDetailsService {

    private LoginDetailsDao loginDetailsDao;

    @Autowired
    public LoginDetailsServiceImpl(LoginDetailsDao loginDetailsDao) {
        this.loginDetailsDao = loginDetailsDao;
    }

    @Override
    public List<LoginDetails> findAll() {
        return loginDetailsDao.findAll();
    }

    @Override
    public LoginDetails findById(int theId) {
        Optional<LoginDetails> res = loginDetailsDao.findById(theId);
        LoginDetails loginDetails = null;
        if (res.isPresent()) {
            loginDetails = res.get();
        }
        else {
            throw new RuntimeException("Login Detail id is not found " + theId);
        }
        return loginDetails;
    }

    @Override
    public LoginDetails save(LoginDetails loginDetails) {
        return loginDetailsDao.save(loginDetails);
    }

    @Override
    public void deleteById(int theId) {
        loginDetailsDao.deleteById(theId);
    }

    @Override
    public String authentication(LoginDetails loginDetails) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<LoginDetails> res = loginDetailsDao.findById(loginDetails.getId());
        if (res.isPresent()) {
            LoginDetails newLoginDetails = res.get();
            if (bcrypt.matches(loginDetails.getPassword(), loginDetails.getPassword())) {
                return "Authenticate user";
            } else {
                return "Incorrect Password";
            }
        }
        throw new RuntimeException("No use is found for this email!!");
    }
}
