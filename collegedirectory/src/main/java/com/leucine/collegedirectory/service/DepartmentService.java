package com.leucine.collegedirectory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leucine.collegedirectory.exception.NoRecordFoundException;
import com.leucine.collegedirectory.model.Department;
import com.leucine.collegedirectory.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NoRecordFoundException("Department not found with ID: " + id));
    }

//    public Department getDepartmentByName(String name) {
//        return departmentRepository.findByName(name)
//                .orElseThrow(() -> new NoRecordFoundException("Department not found with name: " + name));
//    }

    public Department createOrUpdateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new NoRecordFoundException("Department not found with ID: " + id);
        }
        departmentRepository.deleteById(id);
    }
}