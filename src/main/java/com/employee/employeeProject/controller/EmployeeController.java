package com.employee.employeeProject.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import javax.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeeProject.bean.EmployeeRequest;
import com.employee.employeeProject.model.Employee;
import com.employee.employeeProject.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee addEmployee(@RequestBody Employee employee) {
	    return employeeService.addEmployee(employee);
	}

	@GetMapping
	public List<Employee> getAllEmployees(){
	    return employeeService.getAllEmployees();
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
      try {
    	  employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(RuntimeException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
	
    
    @PutMapping("/updateEmployee")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {  
      try {
    	  employeeService.updateEmployee(employee);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(NoSuchElementException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/employeeByStartDateAndIncome")
    public List<Employee> getEmployeeByStartDateAndIncome(@Validated @RequestBody EmployeeRequest input){
    	LocalDate startDate = LocalDate.of(input.getYear(), input.getMonth(), input.getDay());
    	return employeeService.getEmployeeByStartDateAndIncome(startDate, input.getSalary());
    }
    
    @PutMapping("/updateLocationByDepartment/{departmentId}/{location}")
    public ResponseEntity<String> updateLocationByDepartment(@PathVariable int departmentId, @PathVariable String location) throws SQLException {  
      try {
    	  employeeService.updateLocationByDepartment(location, departmentId);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(NoSuchElementException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    
	@PostMapping("/findWinnerEmployeeByMonth")
	public int findWinnerEmployeeByMonth(){
	    return employeeService.findWinnerEmployeeByMonth();
	}

}
