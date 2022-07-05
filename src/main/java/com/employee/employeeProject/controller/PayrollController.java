package com.employee.employeeProject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeeProject.model.Payroll;
import com.employee.employeeProject.service.PayrollService;

@RestController
@RequestMapping("/Payroll")
public class PayrollController {

	@Autowired
	private PayrollService payrollService;//inject
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Payroll addPayroll(@RequestBody Payroll payroll) {
	    return payrollService.addPayroll(payroll);
	  }

	@GetMapping
	public List<Payroll> getAllPayroll(){
	    return payrollService.getAllPayroll();
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayroll(@PathVariable int id){
      try {
    	  payrollService.deletePayrollById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(RuntimeException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
	
    
    @PutMapping("/updatePayroll")
    public ResponseEntity<String> updatePayroll(@RequestBody Payroll payroll) {  
      try {
    	  payrollService.updatePayroll(payroll);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(NoSuchElementException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    

}
