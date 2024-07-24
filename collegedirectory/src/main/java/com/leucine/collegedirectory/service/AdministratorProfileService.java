package com.leucine.collegedirectory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.exception.DuplicateDataException;
import com.leucine.collegedirectory.exception.InvalidDataException;
import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.AdministratorProfile;
import com.leucine.collegedirectory.model.Department;
import com.leucine.collegedirectory.model.FacultyProfile;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.repository.AdministratorProfileRepository;
import com.leucine.collegedirectory.repository.DepartmentRepository;
import com.leucine.collegedirectory.repository.FacultyProfileRepository;
import com.leucine.collegedirectory.repository.StudentProfileRepository;
import com.leucine.collegedirectory.repository.UsersRepository;

@Service
public class AdministratorProfileService {
	
	@Autowired
    private StudentProfileRepository studentRepository;
	
	@Autowired
	private UsersRepository userRepository;

    @Autowired
    private FacultyProfileRepository facultyRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;
    
    
    public AdministratorProfile addadminProfile(AdministratorProfile adminProfile) {
        System.out.println(adminProfile.toString());
        if (adminProfile == null) {
            throw new InvalidDataException("The student data you have provided is null");
        }
        Optional<AdministratorProfile> admin = administratorProfileRepository.findByUsername(adminProfile.getUsername());
        if (admin.isPresent()) {
            throw new DuplicateDataException("Student with this username already exists");
        }
        return administratorProfileRepository.save(adminProfile);
    }
	
    public StudentProfile addStudentProfile(StudentProfile studentProfile) {
    	if (studentProfile == null) {
            throw new InvalidDataException("The student data you have provided is null");
        }

        // Check if the department exists
        Department department = studentProfile.getDepartment();
        if (department != null && department.getId() == null) {
            // Save the department if it's a new department
            department = departmentRepository.save(department);
            studentProfile.setDepartment(department);
        }

        Optional<StudentProfile> student = studentRepository.findByUsername(studentProfile.getUsername());
        if (student.isPresent()) {
            throw new DuplicateDataException("Student with this username already exists");
        }

        return studentRepository.save(studentProfile);
        
    }


	public StudentProfile updateStudentProfile(Long userId, StudentProfile studentProfile) {
		StudentProfile existingProfile = studentRepository.findById(userId)
	            .orElseThrow(() -> new NoRecordFoundException("No student profile found with ID: " + userId));

	    // Update only the fields that are present in the provided profile
	    if (studentProfile.getName() != null) {
	        existingProfile.setName(studentProfile.getName());
	    }
	    if (studentProfile.getEmail() != null) {
	        existingProfile.setEmail(studentProfile.getEmail());
	    }
	    if (studentProfile.getPhone() != null) {
	        existingProfile.setPhone(studentProfile.getPhone());
	    }
	    if (studentProfile.getPhoto() != null) {
	        existingProfile.setPhoto(studentProfile.getPhoto());
	    }
	    if (studentProfile.getYear() != null) {
	        existingProfile.setYear(studentProfile.getYear());
	    }
	    if (studentProfile.getDepartment() != null) {
	        existingProfile.setDepartment(studentProfile.getDepartment());
	    }
	    if (studentProfile.getPassword() != null) {
	        existingProfile.setPassword(studentProfile.getPassword());
	    }
	    if (studentProfile.getUsername() != null) {
	        existingProfile.setUsername(studentProfile.getUsername());
	    }
	    if (studentProfile.getRole() != null) {
	        existingProfile.setRole(studentProfile.getRole());
	    }

	    // Save and return the updated profile
	    return studentRepository.save(existingProfile);
	}

	
	public void deleteStudentProfile(Long userId) {
		if (!studentRepository.existsById(userId)) {
            throw new NoRecordFoundException("No student profile found with ID: " + userId);
        }
        studentRepository.deleteById(userId);
	}

	
	public FacultyProfile addFacultyProfile(FacultyProfile facultyProfile) {
		if(facultyProfile==null) throw new InvalidDataException("The student data you have provided is null");
		Optional<FacultyProfile> faculty = facultyRepository.findByUsername(facultyProfile.getUsername());
		if(faculty.isPresent()) throw new DuplicateDataException("Customer already exists");
		
		return facultyRepository.save(facultyProfile);
	}

	
	public FacultyProfile updateFacultyProfile(Long userId, FacultyProfile facultyProfile) {
		FacultyProfile existingProfile = facultyRepository.findById(userId)
	            .orElseThrow(() -> new NoRecordFoundException("No faculty profile found with ID: " + userId));

	    // Update only the fields that are present in the provided profile
	    if (facultyProfile.getName() != null) {
	        existingProfile.setName(facultyProfile.getName());
	    }
	    if (facultyProfile.getEmail() != null) {
	        existingProfile.setEmail(facultyProfile.getEmail());
	    }
	    if (facultyProfile.getPhone() != null) {
	        existingProfile.setPhone(facultyProfile.getPhone());
	    }
	    if (facultyProfile.getOfficeHours() != null) {
	        existingProfile.setOfficeHours(facultyProfile.getOfficeHours());
	    }
	    if (facultyProfile.getPassword() != null) {
	        existingProfile.setPassword(facultyProfile.getPassword());
	    }
	    if (facultyProfile.getUsername() != null) {
	        existingProfile.setUsername(facultyProfile.getUsername());
	    }
	    if (facultyProfile.getRole() != null) {
	        existingProfile.setRole(facultyProfile.getRole());
	    }

	    // Save and return the updated profile
	    return facultyRepository.save(existingProfile);
	}

	
	public void deleteFacultyProfile(Long userId) {
		if (!facultyRepository.existsById(userId)) {
            throw new NoRecordFoundException("No faculty profile found with ID: " + userId);
        }
        facultyRepository.deleteById(userId);

	}

}
