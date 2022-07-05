package com.employee.employeeProject.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.employee.employeeProject.model.Employee;

public interface EmployeeService {
	  Employee addEmployee(Employee employee);
	  Employee getEmployeeById(int employeeId);
	  void updateEmployee(Employee employee);
	  void deleteEmployeeById(int employeeId);
	  List<Employee> getAllEmployees();
	  List<Employee> getEmployeeByStartDateAndIncome(LocalDate startDate, int salary);
	  void updateLocationByDepartment(String location, int departmentId) throws SQLException;
	  int findWinnerEmployeeByMonth();
}
