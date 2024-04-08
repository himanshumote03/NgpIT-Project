package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDetailsDao extends JpaRepository<LoginDetails, Integer> {
}
