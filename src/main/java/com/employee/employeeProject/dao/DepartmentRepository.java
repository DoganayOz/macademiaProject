package com.employee.employeeProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employeeProject.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{


}
