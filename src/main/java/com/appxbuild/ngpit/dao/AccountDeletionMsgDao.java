package com.appxbuild.ngpit.dao;

import com.appxbuild.ngpit.entity.AccountDeletionMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDeletionMsgDao extends JpaRepository<AccountDeletionMsg, Integer> {
}
