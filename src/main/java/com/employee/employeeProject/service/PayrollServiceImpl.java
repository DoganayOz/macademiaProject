package com.employee.employeeProject.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.employee.employeeProject.dao.PayrollRepository;
import com.employee.employeeProject.model.Payroll;

@Service
public class PayrollServiceImpl implements PayrollService {
	
	@Autowired
	  private PayrollRepository payrollRepository;
	  @Override
	  public Payroll addPayroll(Payroll payroll) {
	    return payrollRepository.save(payroll);
	  }
	  @Override
	  public Payroll getPayrollById(int payrollId) {
	    return payrollRepository.findById(payrollId).get();
	  }
	  @Override
	  public List<Payroll> getAllPayroll(){
	    return payrollRepository.findAll();
	  }
	  
	  @Override
	  public void updatePayroll(Payroll payroll) {
		  Payroll payrollDB = payrollRepository.findById(payroll.getId()).orElseThrow();
		  if(payrollDB != null) {
			  payrollRepository.save(payroll);
		  }
	  }
	  
	  @Override
	  public void deletePayrollById(int payrollId) {
	    try {
	    	payrollRepository.deleteById(payrollId);  
	    }catch(DataAccessException ex){
	      throw new RuntimeException(ex.getMessage());
	    }
	  }
	@Override
	public List<Payroll> findBySalaryGraterThan(int salary) {
		return payrollRepository.findBySalaryGreaterThanEqual(BigDecimal.valueOf(salary));
	}
}
