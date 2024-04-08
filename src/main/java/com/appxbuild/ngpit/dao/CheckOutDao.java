package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutDao extends JpaRepository<CheckOut, Integer> {
}
