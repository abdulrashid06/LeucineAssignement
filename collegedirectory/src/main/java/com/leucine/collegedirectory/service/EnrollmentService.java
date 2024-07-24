package com.leucine.collegedirectory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.exception.DuplicateDataException;
import com.leucine.collegedirectory.exception.InvalidDataException;
import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.Enrollment;
import com.leucine.collegedirectory.repository.EnrollmentRepository;


@Service
public class EnrollmentService {
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	public Enrollment createEnrollment(Enrollment enrollment) {
        if (enrollment.getStudent() == null || enrollment.getCourse() == null ) {
            throw new InvalidDataException("Enrollment data is invalid");
        }

        enrollmentRepository.findByStudentIdAndCourseId(
                enrollment.getStudent().getId(),
                enrollment.getCourse().getId()
        ).ifPresent(e -> {
            throw new DuplicateDataException("Duplicate enrollment found");
        });

        return enrollmentRepository.save(enrollment);
    }

	
	
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NoRecordFoundException("Enrollment not found with id: " + id));
    }

    
    
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    
    
    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        Enrollment enrollment = getEnrollmentById(id);

        if (enrollmentDetails.getStudent() != null) {
            enrollment.setStudent(enrollmentDetails.getStudent());
        }
        if (enrollmentDetails.getCourse() != null) {
            enrollment.setCourse(enrollmentDetails.getCourse());
        }

        return enrollmentRepository.save(enrollment);
    }

    
    
    public void deleteEnrollment(Long id) {
        Enrollment enrollment = getEnrollmentById(id);
        enrollmentRepository.delete(enrollment);
    }

}
