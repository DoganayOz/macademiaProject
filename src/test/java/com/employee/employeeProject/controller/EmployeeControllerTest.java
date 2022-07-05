package com.employee.employeeProject.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.employee.employeeProject.model.Employee;
import com.employee.employeeProject.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
class EmployeeControllerTest {
	
	@InjectMocks
	private static EmployeeController employeeController = new EmployeeController();
	
	@Mock
	EmployeeService employeeService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		MockitoAnnotations.initMocks(employeeController);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void test_addEmployee_RequestDolu() throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        formatter = formatter.withLocale(Locale.US);
        LocalDate date = LocalDate.parse("2022-agu-01", formatter);
         
        Employee employee = new Employee(1, "Doganay", "Oz", "Turkey","1", date);
        Employee result = employeeController.addEmployee(employee);
        
        Assert.assertTrue(result.getName().equalsIgnoreCase(employee.getName()) ? true : false);
         
	}
	
	 @Test
	 public void testFindAll() 
	 {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	     formatter = formatter.withLocale(Locale.US);
	     LocalDate date = LocalDate.parse("2022-agu-01", formatter);
	        
	     Employee employee1 = new Employee(1, "Doganay", "Oz", "Turkey","1", date);
	     Employee employee2 = new Employee(1, "Marc", "Jash", "US","1", date);
	     List<Employee> employeeList = new ArrayList<>();
	     employeeList.set(1,employee1);
	     employeeList.set(2,employee2);
	 
	     when(employeeService.getAllEmployees()).thenReturn(employeeList);
	 
	     List<Employee> result = employeeController.getAllEmployees();
	 
	     Assert.assertTrue(result.get(0).getName().equalsIgnoreCase(employee1.getName()) ? true : false);
	  }

}
