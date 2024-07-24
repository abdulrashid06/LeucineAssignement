package com.leucine.collegedirectory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leucine.collegedirectory.model.AdministratorProfile;
import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.model.Users;

public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
	
	Optional<AdministratorProfile> findByEmail(String email);
	Optional<AdministratorProfile> findByUsername(String username);

}
