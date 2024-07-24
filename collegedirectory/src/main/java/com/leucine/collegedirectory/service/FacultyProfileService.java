package com.leucine.collegedirectory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.Department;
import com.leucine.collegedirectory.model.FacultyProfile;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.repository.DepartmentRepository;
import com.leucine.collegedirectory.repository.FacultyProfileRepository;
import com.leucine.collegedirectory.repository.StudentProfileRepository;


@Service
public class FacultyProfileService {
	
	@Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    
    // Fetch faculty profile details
    public FacultyProfile getFacultyProfile(Long id) {
        Optional<FacultyProfile> faculty = facultyProfileRepository.findById(id);
        if (faculty.isEmpty()) {
            throw new NoRecordFoundException("Faculty not found");
        }
        return faculty.get();
    }

    // Fetch students in the faculty member's classes
    public List<StudentProfile> getClassList(Long facultyId) {
        // Assuming FacultyProfile has a relationship with courses and students
        Optional<FacultyProfile> faculty = facultyProfileRepository.findById(facultyId);
        if (faculty.isEmpty()) {
            throw new NoRecordFoundException("Faculty not found");
        }
        // This is a placeholder; you would need to implement logic to fetch students based on faculty's courses
        return studentProfileRepository.findAll(); // Replace with actual logic
    }

    // Update faculty profile
    public FacultyProfile updateFacultyProfile(Long id, FacultyProfile updatedFaculty) {
        Optional<FacultyProfile> existingFaculty = facultyProfileRepository.findById(id);
        if (existingFaculty.isEmpty()) {
            throw new NoRecordFoundException("Faculty not found");
        }
        FacultyProfile faculty = existingFaculty.get();
        faculty.setOfficeHours(updatedFaculty.getOfficeHours());
        faculty.setEmail(updatedFaculty.getEmail());
        faculty.setPhone(updatedFaculty.getPhone());
        return facultyProfileRepository.save(faculty);
    }
    
    // Add a new/exiting faculty member with department
    public FacultyProfile addFacultywithDepartment(FacultyProfile facultyProfile, long id) {
    	
        Department d= departmentRepository.findById(id)
                    .orElseThrow(() -> new NoRecordFoundException("Department not found"));
        
        facultyProfile.setDepartment(d);
        return facultyProfileRepository.save(facultyProfile);
    }
    
    public FacultyProfile addFaculty(FacultyProfile facultyProfile) {
        // Validate department
        return facultyProfileRepository.save(facultyProfile);
    }
    
    // Delete a faculty member
    public void deleteFaculty(Long id) {
        FacultyProfile existingFaculty = facultyProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        facultyProfileRepository.delete(existingFaculty);
    }

    // Get list of all faculty members
    public List<FacultyProfile> getAllFaculty() {
        return facultyProfileRepository.findAll();
    }

}
