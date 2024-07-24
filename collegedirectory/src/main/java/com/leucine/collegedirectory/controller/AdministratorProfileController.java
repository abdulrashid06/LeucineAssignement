package com.leucine.collegedirectory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.exception.DuplicateDataException;
import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.AdministratorProfile;
import com.leucine.collegedirectory.model.FacultyProfile;
import com.leucine.collegedirectory.model.Role;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.service.AdministratorProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdministratorProfileController {
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private AdministratorProfileService administratorService;

	@PostMapping("/addfaculty")
	public ResponseEntity<FacultyProfile> addFacultyProfile(@RequestBody FacultyProfile facultyProfile) {
		try {
			facultyProfile.setPassword(passwordEncoder.encode(facultyProfile.getPassword()));
			facultyProfile.setRole(Role.FACULTY_MEMBER);
			FacultyProfile savedProfile = administratorService.addFacultyProfile(facultyProfile);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
		} catch (DuplicateDataException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	 @PostMapping("/addstudent")
	    public ResponseEntity<StudentProfile> addStudentProfile(@Valid @RequestBody StudentProfile studentProfile) {
	        try {
	            studentProfile.setPassword(passwordEncoder.encode(studentProfile.getPassword()));
	            studentProfile.setRole(Role.STUDENT);
	            StudentProfile savedProfile = administratorService.addStudentProfile(studentProfile);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
	        } catch (DuplicateDataException e) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	        } catch (Exception e) {
	            // Log the exception
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
    
    
    @PostMapping("/addadmin")
    public ResponseEntity<AdministratorProfile> addadminProfile(@Valid @RequestBody AdministratorProfile adminProfile) {
        try {
        	adminProfile.setPassword(passwordEncoder.encode(adminProfile.getPassword()));
        	adminProfile.setRole(Role.ADMINISTRATOR);
        	AdministratorProfile savedProfile = administratorService.addadminProfile(adminProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProfile);
        } catch (DuplicateDataException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentProfile> updateStudentProfile(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        try {
            StudentProfile updatedProfile = administratorService.updateStudentProfile(id, studentProfile);
            return ResponseEntity.ok(updatedProfile);
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
        try {
            administratorService.deleteStudentProfile(id);
            return ResponseEntity.noContent().build();
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/faculty/{id}")
    public ResponseEntity<FacultyProfile> updateFacultyProfile(@PathVariable Long id, @RequestBody FacultyProfile facultyProfile) {
        try {
            FacultyProfile updatedProfile = administratorService.updateFacultyProfile(id, facultyProfile);
            return ResponseEntity.ok(updatedProfile);
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/faculty/{id}")
    public ResponseEntity<Void> deleteFacultyProfile(@PathVariable Long id) {
        try {
            administratorService.deleteFacultyProfile(id);
            return ResponseEntity.noContent().build();
        } catch (NoRecordFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return new ResponseEntity<>("running", HttpStatus.OK);
    }

    
    

}
