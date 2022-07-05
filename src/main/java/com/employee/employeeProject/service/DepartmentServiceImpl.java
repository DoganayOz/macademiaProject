package com.employee.employeeProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.employee.employeeProject.dao.DepartmentRepository;
import com.employee.employeeProject.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	  private DepartmentRepository departmentRepository;
	  @Override
	  public Department addDepartment(Department department) {
	    return departmentRepository.save(department);
	  }
	  @Override
	  public Department getDepartmentById(int departmentId) {
	    return departmentRepository.findById(departmentId).get();
	  }
	  @Override
	  public List<Department> getAllDepartments(){
	    return departmentRepository.findAll();
	  }
	  
	  @Override
	  public void updateDepartment(Department department) {
		  Department departmentDB = departmentRepository.findById(department.getId()).orElseThrow();
		  if(departmentDB != null) {
			  departmentRepository.save(department);
		  }
	  }
	  
	  @Override
	  public void deleteDepartmentById(int departmentId) {
	    try {
	    	departmentRepository.deleteById(departmentId);  
	    }catch(DataAccessException ex){
	      throw new RuntimeException(ex.getMessage());
	    }
	  }
}
