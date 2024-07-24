package com.leucine.collegedirectory.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.FacultyProfile;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.repository.StudentProfileRepository;
import com.leucine.collegedirectory.service.StudentProfileService;


@RestController
@RequestMapping("/api/student")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentService;
    @Autowired
    private StudentProfileRepository studentRepository;

    @GetMapping("/getallstudents")
    public ResponseEntity<List<StudentProfile>> getAllStudentProfiles() {
        try {
            List<StudentProfile> students = studentService.getAllStudentProfile();
            return ResponseEntity.ok(students);
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentProfileById(@PathVariable Long id) {
        try {
            StudentProfile student = studentService.getStudentProfile(id);
            return ResponseEntity.ok(student);
                    
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
//    
//    @GetMapping("/signIn")
//    public ResponseEntity<StudentProfile> logInUserHandler(Authentication auth){
//        java.util.Optional<StudentProfile> opt= studentRepository.findByEmail(auth.getName());
//        if(opt.isEmpty()) throw new NoRecordFoundException("No user found") ;
//        StudentProfile admin = opt.get();
//        return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
//    }
//
//    @PostMapping("/signIn")
//    public ResponseEntity<StudentProfile> logInUserHandler(@RequestBody String token){
//        String username = JwtToken.decodeJwt(token);
//        java.util.Optional<StudentProfile> opt= studentRepository.findByEmail(username);
//        if(opt.isEmpty()) throw new NoRecordFoundException("No user found") ;
//        return new ResponseEntity<>(opt.get(), HttpStatus.ACCEPTED);
//    }


}
