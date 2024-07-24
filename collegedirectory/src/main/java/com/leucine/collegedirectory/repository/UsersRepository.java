package com.leucine.collegedirectory.repository;

import com.leucine.collegedirectory.model.StudentProfile;
import com.leucine.collegedirectory.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	Optional<Users> findByEmail(String email);
	Optional<Users> findByUsername(String username);

}
