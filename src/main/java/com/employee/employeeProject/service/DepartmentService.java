package com.employee.employeeProject.service;

import java.util.List;

import com.employee.employeeProject.model.Department;


public interface DepartmentService {
	Department addDepartment(Department department);
	Department getDepartmentById(int departmentId);
	  void updateDepartment(Department department);
	  void deleteDepartmentById(int departmentId);
	  List<Department> getAllDepartments();
}
