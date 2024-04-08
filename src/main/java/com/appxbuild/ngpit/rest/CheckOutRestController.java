package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.entity.CheckIn;
import com.appxbuild.ngpit.entity.CheckOut;
import com.appxbuild.ngpit.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CheckOutRestController {

    private CheckOutService checkOutService;

    @Autowired
    public CheckOutRestController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @GetMapping("/checkOut")
    public List<CheckOut> findAll() {
        return checkOutService.findAll();
    }

    @GetMapping("/checkOut/{id}")
    public CheckOut getCheckOutById(@PathVariable int id) {
        CheckOut checkOut = checkOutService.findById(id);
        if (checkOut == null) {
            throw new RuntimeException("CheckOut id is not found " + id);
        }
        return checkOut;
    }

    @GetMapping("/checkOut/login/{loginId}")
    public ResponseEntity<List<CheckOut>> getCheckInsByLoginId(@PathVariable int loginId) {
        List<CheckOut> checkOuts = checkOutService.findAll()
                .stream()
                .filter(checkOut -> checkOut.getLoginDetails() != null && checkOut.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(checkOuts);
    }

    @PostMapping("/checkOut")
    public CheckOut addCheckOut(@RequestBody CheckOut checkOut) {
        checkOut.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        checkOut.setCreated(dt);
        CheckOut newCheckOut = checkOutService.save(checkOut);
        return newCheckOut;
    }

    @PutMapping("/checkOut")
    public CheckOut updateCheckOut(@RequestBody CheckOut checkOut) {
        CheckOut existingCheckOut = checkOutService.findById(checkOut.getId());

        if (existingCheckOut == null) {
            throw new RuntimeException("Login Detail with id " + checkOut.getId() + " not found");
        }
        checkOut.setCreated(existingCheckOut.getCreated());

        CheckOut newCheckOut = checkOutService.save(checkOut);
        return newCheckOut;
    }

    @DeleteMapping("/checkOut/{id}")
    public String deleteCheckOut(@PathVariable int id) {
        CheckOut checkOut = checkOutService.findById(id);
        if (checkOut == null) {
            throw new RuntimeException("CheckOut id is not found " + id);
        }
        checkOutService.deleteById(id);
        return "Deleted Checkout Id " + id;
    }
}
