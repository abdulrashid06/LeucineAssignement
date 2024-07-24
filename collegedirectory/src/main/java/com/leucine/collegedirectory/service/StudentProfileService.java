package com.leucine.collegedirectory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.exception.DuplicateDataException;
import com.leucine.collegedirectory.exception.InvalidDataException;
import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.repository.FacultyProfileRepository;
import com.leucine.collegedirectory.repository.StudentProfileRepository;


@Service
public class StudentProfileService {
	
	@Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    // Fetch student's profile details
    public StudentProfile getStudentProfile(Long id) {
        Optional<StudentProfile> student = studentProfileRepository.findById(id);
        if (student.isEmpty()) {
            throw new NoRecordFoundException("Student with id " + id + " not found");
        }
        return student.get();
    }

    // Add a new student profile
    public StudentProfile addStudent(StudentProfile s) {
        // Check if student with the same username already exists
        Optional<StudentProfile> existingStudent = studentProfileRepository.findByEmail(s.getEmail());
        if (existingStudent.isPresent()) {
            throw new DuplicateDataException("Student with username " + s.getUsername() + " already exists");
        }

        // Validate student profile data if necessary
        if (s.getName() == null || s.getYear() == null) {
            throw new InvalidDataException("Student profile data is incomplete");
        }
        return studentProfileRepository.save(s);
    }

    // Update an existing student profile
    public StudentProfile updateStudent(Long id, StudentProfile updatedStudent) {
        Optional<StudentProfile> existingStudent = studentProfileRepository.findById(id);
        if (existingStudent.isEmpty()) {
            throw new NoRecordFoundException("Student with id " + id + " not found");
        }
        StudentProfile student = existingStudent.get();
        student.setName(updatedStudent.getName());
        student.setYear(updatedStudent.getYear());
        student.setPhoto(updatedStudent.getPhoto());
        student.setDepartment(updatedStudent.getDepartment());
        return studentProfileRepository.save(student);
    }

    // Delete a student profile
    public void deleteStudent(Long id) {
        if (!studentProfileRepository.existsById(id)) {
            throw new NoRecordFoundException("Student with id " + id + " not found");
        }
        studentProfileRepository.deleteById(id);
    }

    // Search for students based on filters using Stream API
    public List<StudentProfile> searchStudents(String name, Long departmentId, String year) {
        return studentProfileRepository.searchStudents(name, departmentId, year);
    }

	public StudentProfile findByUserName(String email) {
		// TODO Auto-generated method stub
        Optional<StudentProfile> student = studentProfileRepository.findByEmail(email);
        if (student.isEmpty()) {
            throw new NoRecordFoundException("Student with id " + email + " not found");
        }
        return student.get();
	}
	
	public List<StudentProfile> getAllStudentProfile(){
		return studentProfileRepository.findAll();
	}

}
