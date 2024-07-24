package com.leucine.collegedirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.model.Enrollment;
import com.leucine.collegedirectory.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
	
	@Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/getallenrollments")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }

    @PostMapping("/create-enrollment")
    public ResponseEntity<Enrollment> enrollStudent(@Valid @RequestBody Enrollment enrollment) {
        Enrollment enrolled = enrollmentService.createEnrollment(enrollment);
        return ResponseEntity.ok(enrolled);
    }

}
