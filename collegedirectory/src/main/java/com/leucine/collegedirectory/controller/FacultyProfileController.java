package com.leucine.collegedirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.FacultyProfile;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.repository.FacultyProfileRepository;
import com.leucine.collegedirectory.service.FacultyProfileService;

@RestController
@RequestMapping("/api/faculty")
public class FacultyProfileController {
	

    @Autowired
    private FacultyProfileService facultyService;
    
    @Autowired
    private FacultyProfileRepository facultyRepository;


    // Fetch faculty profile details
    @GetMapping("/{id}")
    public ResponseEntity<FacultyProfile> getFacultyProfile(@PathVariable Long id) {
        FacultyProfile facultyProfile = facultyService.getFacultyProfile(id);
        return ResponseEntity.ok(facultyProfile);
    }

    // Fetch class list of a faculty member
    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentProfile>> getClassList(@PathVariable Long id) {
        List<StudentProfile> students = facultyService.getClassList(id);
        return ResponseEntity.ok(students);
    }

    // Update faculty profile
    @PutMapping("/{id}")
    public ResponseEntity<FacultyProfile> updateFacultyProfile(
            @PathVariable Long id, 
            @RequestBody FacultyProfile updatedFaculty) {
        FacultyProfile facultyProfile = facultyService.updateFacultyProfile(id, updatedFaculty);
        return ResponseEntity.ok(facultyProfile);
    }
    
    
//    @GetMapping("/signIn")
//    public ResponseEntity<FacultyProfile> logInUserHandler(Authentication auth){
//        java.util.Optional<FacultyProfile> opt= facultyRepository.findByEmail(auth.getName());
//        if(opt.isEmpty()) throw new NoRecordFoundException("No user found") ;
//        FacultyProfile admin = opt.get();
//        return new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
//    }
//
//    @PostMapping("/signIn")
//    public ResponseEntity<FacultyProfile> logInUserHandler(@RequestBody String token){
//        String username = JwtToken.generateToken(token);
//        java.util.Optional<FacultyProfile> opt= facultyRepository.findByEmail(username);
//        if(opt.isEmpty()) throw new NoRecordFoundException("No user found") ;
//        return new ResponseEntity<>(opt.get(), HttpStatus.ACCEPTED);
//    }

}
