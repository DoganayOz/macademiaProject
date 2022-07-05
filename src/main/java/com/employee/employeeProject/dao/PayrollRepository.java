package com.employee.employeeProject.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.employeeProject.model.Employee;
import com.employee.employeeProject.model.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Integer>{

	List<Payroll> findBySalaryGreaterThanEqual(BigDecimal salary);

}
