package com.leucine.collegedirectory.repository;

import com.leucine.collegedirectory.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
