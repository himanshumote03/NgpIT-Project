package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInDao extends JpaRepository<CheckIn, Integer> {
}
