package com.leucine.collegedirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.model.Course;
import com.leucine.collegedirectory.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	
	@Autowired
    private CourseService courseService;

    @GetMapping("/getallcourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course) {
        Course addedCourse = courseService.addCourse(course);
        return ResponseEntity.ok(addedCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        Course updatedCourse = courseService.updateCourse(course);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
