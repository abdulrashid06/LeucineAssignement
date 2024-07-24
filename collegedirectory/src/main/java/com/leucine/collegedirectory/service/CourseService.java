package com.leucine.collegedirectory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.model.Course;
import com.leucine.collegedirectory.repository.CourseRepository;


@Service
public class CourseService {
	
	@Autowired
    private CourseRepository courseRepository;

    // Fetch all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Add a new course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update course details
    public Course updateCourse(Course course) {
        if (!courseRepository.existsById(course.getId())) {
            throw new RuntimeException("Course not found");
        }
        return courseRepository.save(course);
    }

    // Remove a course
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(id);
    }

}
