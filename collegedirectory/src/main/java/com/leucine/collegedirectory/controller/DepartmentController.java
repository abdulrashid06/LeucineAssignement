package com.leucine.collegedirectory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leucine.collegedirectory.model.Department;
import com.leucine.collegedirectory.service.DepartmentService;

@RestController
@RequestMapping("/api/department") // Base path for all endpoints in this controller
public class DepartmentController {
	
	@Autowired
    private DepartmentService departmentService;

    @GetMapping("/getalldepartments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    // Uncomment this method if needed and make sure its path is unique
    // @GetMapping("/name/{name}")
    // public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
    //     Department department = departmentService.getDepartmentByName(name);
    //     return ResponseEntity.ok(department);
    // }

    @PostMapping
    public ResponseEntity<Department> createOrUpdateDepartment(@RequestBody Department department) {
        Department savedDepartment = departmentService.createOrUpdateDepartment(department);
        return ResponseEntity.ok(savedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}