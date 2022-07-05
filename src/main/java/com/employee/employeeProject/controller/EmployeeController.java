package com.employee.employeeProject.controller;

import java.sql.SQLException;
import java.time.LocalDate;
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
        // log the error message
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
        // log the error message
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    
    @PostMapping("/employeeByStartDateAndIncome/{day}/{month}/{year}/{salary}")
    public List<Employee> getEmployeeByStartDateAndIncome(@PathVariable("day") int day, @PathVariable("month") int month,
			@PathVariable("year") int year, @PathVariable int salary){
    	LocalDate startDate = LocalDate.of(year, month, day);
    	return employeeService.getEmployeeByStartDateAndIncome(startDate, salary);
    }
    
    @PutMapping("/updateLocationByDepartment/{departmentId}/{location}")
    public ResponseEntity<String> updateLocationByDepartment(@PathVariable int departmentId, @PathVariable String location) throws SQLException {  
      try {
    	  employeeService.updateLocationByDepartment(location, departmentId);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(NoSuchElementException ex){
        // log the error message
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    
	@GetMapping("/findWinnerEmployeeByMonth")
	public int findWinnerEmployeeByMonth(){
	    return employeeService.findWinnerEmployeeByMonth();
	}

}
