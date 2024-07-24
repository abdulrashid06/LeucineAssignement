package com.leucine.collegedirectory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leucine.collegedirectory.model.StudentProfile;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
	
	Optional<StudentProfile> findByEmail(String email);
	Optional<StudentProfile> findByUsername(String username);
	
	@Query("SELECT s FROM StudentProfile s WHERE " +
	           "(:name IS NULL OR s.name LIKE %:name%) AND " +
	           "(:departmentId IS NULL OR s.department.id = :departmentId) AND " +
	           "(:year IS NULL OR s.year = :year)")
	    List<StudentProfile> searchStudents(@Param("name") String name,
	                                        @Param("departmentId") Long departmentId,
	                                        @Param("year") String year);

}
