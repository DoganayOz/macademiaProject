package com.employee.employeeProject.service;

import java.util.List;

import com.employee.employeeProject.model.Payroll;


public interface PayrollService {
	Payroll addPayroll(Payroll payroll);
	Payroll getPayrollById(int payrollId);
	void updatePayroll(Payroll payroll);
	void deletePayrollById(int payrollId);
	List<Payroll> getAllPayroll();
	List<Payroll> findBySalaryGraterThan(int salary);
}
