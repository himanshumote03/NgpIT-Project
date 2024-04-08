package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.entity.AccountDeletionMsg;

import java.util.List;

public interface AccountDeletionMsgService {
    List<AccountDeletionMsg> findAll();
    AccountDeletionMsg findById(int theId);
    AccountDeletionMsg save(AccountDeletionMsg theAccountDeletionMsg);
    void deleteById(int theId);
}
