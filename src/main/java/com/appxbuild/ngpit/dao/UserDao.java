package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
}
