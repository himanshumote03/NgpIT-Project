package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.entity.AccountDeletionMsg;
import com.appxbuild.ngpit.entity.CheckIn;
import com.appxbuild.ngpit.entity.User;
import com.appxbuild.ngpit.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CheckInRestController {

    private CheckInService checkInService;

    @Autowired
    public CheckInRestController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @GetMapping("/checkIn")
    public List<CheckIn> findAll() {
        return checkInService.findAll();
    }

    @GetMapping("/checkIn/{id}")
    public CheckIn getCheckInById(@PathVariable int id) {
        CheckIn checkIn = checkInService.findById(id);
        if (checkIn == null) {
            throw new RuntimeException("CheckIn id is not found " + id);
        }
        return checkIn;
    }

    @GetMapping("/checkIn/login/{loginId}")
    public ResponseEntity<List<CheckIn>> getCheckInsByLoginId(@PathVariable int loginId) {
        List<CheckIn> checkIns = checkInService.findAll()
                .stream()
                .filter(checkIn -> checkIn.getLoginDetails() != null && checkIn.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(checkIns);
    }

    @PostMapping("/checkIn")
    public CheckIn addCheckIn(@RequestBody CheckIn checkIn) {
        checkIn.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        checkIn.setCreated(dt);
        CheckIn newCheckIn = checkInService.save(checkIn);
        return newCheckIn;
    }

    @PutMapping("/checkIn")
    public CheckIn updateCheckIn(@RequestBody CheckIn checkIn) {
        CheckIn existingCheckIn = checkInService.findById(checkIn.getId());

        if (existingCheckIn == null) {
            throw new RuntimeException("Login Detail with id " + checkIn.getId() + " not found");
        }
        checkIn.setCreated(existingCheckIn.getCreated());

        CheckIn newCheckIn = checkInService.save(checkIn);
        return newCheckIn;
    }

    @DeleteMapping("/checkIn/{id}")
    public String deleteCheckIn(@PathVariable int id) {
        CheckIn checkIn = checkInService.findById(id);
        if (checkIn == null) {
            throw new RuntimeException("CheckIn id is not found " + id);
        }
        checkInService.deleteById(id);
        return ("Deleted CheckIn id " + id);
    }
}
