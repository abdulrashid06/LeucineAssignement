package com.leucine.collegedirectory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.collegedirectory.model.FacultyProfile;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.model.Users;

public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
	
	Optional<FacultyProfile> findByEmail(String email);
	Optional<FacultyProfile> findByUsername(String username);

}
