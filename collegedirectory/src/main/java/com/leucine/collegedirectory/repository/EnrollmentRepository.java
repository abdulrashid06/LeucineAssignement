package com.leucine.collegedirectory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.collegedirectory.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	
	Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

}
