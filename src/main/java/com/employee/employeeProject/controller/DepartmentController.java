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

import com.employee.employeeProject.model.Department;
import com.employee.employeeProject.service.DepartmentService;

@RestController
@RequestMapping("/Department")
public class DepartmentController {	
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Department addDepartment(@RequestBody Department department) {
	    return departmentService.addDepartment(department);
	  }

	@GetMapping
	public List<Department> getAllDepartments(){
	    return departmentService.getAllDepartments();
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id){
      try {
    	  departmentService.deleteDepartmentById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(RuntimeException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
	
    
    @PutMapping("/updateDepartment")
    public ResponseEntity<String> updateDepartment(@RequestBody Department department) {  
      try {
    	  departmentService.updateDepartment(department);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(NoSuchElementException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    
}
