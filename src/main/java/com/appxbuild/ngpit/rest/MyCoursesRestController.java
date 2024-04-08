package com.appxbuild.ngpit.rest;

import com.appxbuild.ngpit.entity.CheckIn;
import com.appxbuild.ngpit.entity.CheckOut;
import com.appxbuild.ngpit.entity.MyCourses;
import com.appxbuild.ngpit.service.MyCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MyCoursesRestController {

    private MyCoursesService myCoursesService;

    @Autowired
    public MyCoursesRestController(MyCoursesService myCoursesService) {
        this.myCoursesService = myCoursesService;
    }


    @GetMapping("/myCourses")
    public List<MyCourses> findAll() {
        return myCoursesService.findALl();
    }

    @GetMapping("/myCourses/{id}")
    public MyCourses getMyCOursesById(@PathVariable int id) {

        MyCourses myCourses = myCoursesService.findById(id);
        if (myCourses == null) {
            throw new RuntimeException("MyCourse id is not found " + id);
        }
        return myCourses;
    }

    @GetMapping("/myCourses/login/{loginId}")
    public ResponseEntity<List<MyCourses>> getCheckInsByLoginId(@PathVariable int loginId) {
        List<MyCourses> myCourses = myCoursesService.findALl()
                .stream()
                .filter(myCourses1 -> myCourses1.getLoginDetails() != null && myCourses1.getLoginDetails().getId() == loginId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(myCourses);
    }

    @PostMapping("/myCourses")
    public MyCourses addMyCourse(@RequestBody MyCourses myCourses) {
        myCourses.setId(0);
        LocalDateTime dt = LocalDateTime.now();
        myCourses.setCreated(dt);
        MyCourses newMyCourses = myCoursesService.save(myCourses);
        return newMyCourses;
    }

    @PutMapping("/myCourses")
    public MyCourses updateMyCourse(@RequestBody MyCourses myCourses) {
        MyCourses existingMyCourses = myCoursesService.findById(myCourses.getId());

        if (existingMyCourses == null) {
            throw new RuntimeException("Login Detail with id " + myCourses.getId() + " not found");
        }
        myCourses.setCreated(existingMyCourses.getCreated());

        MyCourses newMyCourse = myCoursesService.save(myCourses);
        return newMyCourse;
    }

    @DeleteMapping("/myCourses/{id}")
    public String deleteMyCourse(@PathVariable int id) {
        MyCourses myCourses = myCoursesService.findById(id);
        if (myCourses == null) {
            throw new RuntimeException("MyCourse id is not found " + id);
        }
        myCoursesService.deleteById(id);
        return ("Deleted MyCourse id " + id);
    }

}
