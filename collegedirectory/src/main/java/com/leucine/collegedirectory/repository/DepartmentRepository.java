package com.leucine.collegedirectory.repository;

import com.leucine.collegedirectory.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
