package com.appxbuild.ngpit.rest;


import com.appxbuild.ngpit.entity.LoginDetails;
import com.appxbuild.ngpit.service.AccountDeletionMsgService;
import com.appxbuild.ngpit.entity.AccountDeletionMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountDeletionMsgRestController {

    @Autowired
    private AccountDeletionMsgService accountDeletionMsgService;

    public AccountDeletionMsgRestController(AccountDeletionMsgService accountDeletionMsgService) {
        this.accountDeletionMsgService = accountDeletionMsgService;
    }

    @GetMapping("/accountDeletionMsg")
    public List<AccountDeletionMsg> findAll() {
        return accountDeletionMsgService.findAll();
    }

    @GetMapping("/accountDeletionMsg/{id}")
    public AccountDeletionMsg getAccountDeletionMsgById(@PathVariable int id) {
        AccountDeletionMsg accountDeletionMsg = accountDeletionMsgService.findById(id);
        if (accountDeletionMsg == null) {
            throw new RuntimeException("Account Deletion Message id is not found" + id);
        }
        return accountDeletionMsg;
    }

    @PostMapping("/accountDeletionMsg")
    public AccountDeletionMsg addAccountDeletionMsg(@RequestBody AccountDeletionMsg accountDeletionMsg) {
        LocalDateTime dt = LocalDateTime.now();
        accountDeletionMsg.setCreated(dt);
        AccountDeletionMsg newAccountDeletionMsg = accountDeletionMsgService.save(accountDeletionMsg);
        return newAccountDeletionMsg;
    }

    @PutMapping("/accountDeletionMsg")
    public AccountDeletionMsg updateAccountDeletionMsg(@RequestBody AccountDeletionMsg accountDeletionMsg) {

        AccountDeletionMsg existingAccountDeletionMsg = accountDeletionMsgService.findById(accountDeletionMsg.getId());

        if (existingAccountDeletionMsg == null) {
            throw new RuntimeException("Login Detail with id " + accountDeletionMsg.getId() + " not found");
        }
        accountDeletionMsg.setCreated(existingAccountDeletionMsg.getCreated());

        AccountDeletionMsg newAccountDeletionMsg = accountDeletionMsgService.save(accountDeletionMsg);
        return newAccountDeletionMsg;
    }

    @DeleteMapping("/accountDeletionMsg/{id}")
    public String deleteAccountDeletionMsg(@PathVariable int id) {
        AccountDeletionMsg accountDeletionMsg = accountDeletionMsgService.findById(id);

        if (accountDeletionMsg == null) {
            throw new RuntimeException("Account Deletion Message id is not found " + id);
        }
        accountDeletionMsgService.deleteById(id);
        return "Deleted Account Deletion Message id " + id;
    }
}
