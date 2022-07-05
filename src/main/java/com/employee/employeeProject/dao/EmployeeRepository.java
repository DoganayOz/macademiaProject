package com.employee.employeeProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employeeProject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	void deleteAllById(int id);
	
	Employee findById(int id);
}
