package com.appxbuild.ngpit.service;

import com.appxbuild.ngpit.dao.AccountDeletionMsgDao;
import com.appxbuild.ngpit.entity.AccountDeletionMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDeletionMsgServiceImpl implements AccountDeletionMsgService {

    private AccountDeletionMsgDao accountDeletionMsgDao;

    @Autowired
    public AccountDeletionMsgServiceImpl(AccountDeletionMsgDao accountDeletionMsgDao) {
        this.accountDeletionMsgDao = accountDeletionMsgDao;
    }

    @Override
    public List<AccountDeletionMsg> findAll() {
        return accountDeletionMsgDao.findAll();
    }

    @Override
    public AccountDeletionMsg findById(int theId) {
        Optional<AccountDeletionMsg> res = accountDeletionMsgDao.findById(theId);
        AccountDeletionMsg accountDeletionMsg = null;
        if (res.isPresent()) {
            accountDeletionMsg = res.get();
        } else {
            throw new RuntimeException("Account Deletion Message id is not found " + theId);
        }
        return accountDeletionMsg;
    }

    @Override
    public AccountDeletionMsg save(AccountDeletionMsg accountDeletionMsg) {
        return accountDeletionMsgDao.save(accountDeletionMsg);
    }

    @Override
    public void deleteById(int theId) {
        accountDeletionMsgDao.deleteById(theId);
    }
}
