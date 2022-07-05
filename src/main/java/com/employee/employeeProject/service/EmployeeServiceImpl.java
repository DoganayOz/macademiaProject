package com.employee.employeeProject.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.employee.employeeProject.dao.DrawPrizeRepository;
import com.employee.employeeProject.dao.EmployeeRepository;
import com.employee.employeeProject.model.DrawPrize;
import com.employee.employeeProject.model.Employee;
import com.employee.employeeProject.model.Payroll;
import com.employee.employeeProject.util.H2JDBCUtils;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	  @Autowired
	  private EmployeeRepository employeeRepository;
	  
	  @Autowired
	  private DrawPrizeRepository drawPrizeRepository;
	  
	  @Autowired
	  private DrawPrizeService drawPrizeService;
	 
	  @Autowired 
	  private PayrollService payrollService;
	  
	  
	  @Override
	  public Employee addEmployee(Employee employee) {
	    return employeeRepository.save(employee);
	  }
	  @Override
	  public Employee getEmployeeById(int employeeId) {
	    return employeeRepository.findById(employeeId);
	  }
	
	  @Override
	  public List<Employee> getAllEmployees(){
	    return employeeRepository.findAll();
	  }
	  
	  @Override
	  public void updateEmployee(Employee employee) {
		  Employee employeeDB = employeeRepository.findById(employee.getId());
		  if(employeeDB != null) {
			  employeeRepository.save(employee);
		  }
	  }
	  
	  @Override
	  public void deleteEmployeeById(int employeeId) {
	    try {
	    	employeeRepository.deleteById(employeeId);  
	    }catch(DataAccessException ex){
	      throw new RuntimeException(ex.getMessage());
	    }
	  }
	  
	  @Override
	  public List<Employee> getEmployeeByStartDateAndIncome(LocalDate startDate, int salary) {
		  
		  List<Payroll> employeePayrollList = payrollService.findBySalaryGraterThan(salary);
		  List<Employee> employeeList = new ArrayList<>();
		  
		  for(Payroll payroll : employeePayrollList) {
			  Employee emp = employeeRepository.findById(payroll.getEmployeeId());
			  if(emp != null && emp.getStartDate().isAfter(startDate)) {
				  employeeList.add(emp);
			  }
		  }

		   return employeeList;
	  }
	  
	  @Override
	  public void updateLocationByDepartment(String location, int departmentId) throws SQLException {
		  
		  String UPDATE_EMPLOYEES_SQL = "update employee set location = ? where department_Id = ? ";
		  
	        try (Connection connection = H2JDBCUtils.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL)) {
	            preparedStatement.setString(1, location);
	            preparedStatement.setInt(2, departmentId);

	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            H2JDBCUtils.printSQLException(e);
	        }
	    }
//	@Override
//	public int findWinnerEmployeeByMonth() {
//		  int employeeId = 0;
//		  
//		 //String SELECT_EMPLOYEES_SQL = "select id from (select id from employee ORDER BY dbms_random.value)  where rownum = 1; ";
//		 String SELECT_EMPLOYEES_SQL = "select id from employee sample(1) where rownum < 2 ";
//		  
//	        try (Connection connection = H2JDBCUtils.getConnection();
//	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_SQL)) {
//
//	        	ResultSet rs = preparedStatement.executeQuery();
//	            
//	            while (rs.next()) {
//	            	employeeId = rs.getInt("id");
//	            }
//	            
//	            if(employeeId != 0) {
//	            	setWinnerEmployeeByMonth(employeeId);
//	            }
//	        } catch (SQLException e) {
//	            H2JDBCUtils.printSQLException(e);
//	        }
//		return employeeId;
//	}
	
	
	@Override
	public int findWinnerEmployeeByMonth() {
		  List<Employee> employeeList = new ArrayList();
		  employeeList = getAllEmployees();
		  int winnerEmployeeId = 0;
		  
		  Random rand = new Random();
		  if(employeeList.size() > 0) {
			  winnerEmployeeId = employeeList.get(rand.nextInt(employeeList.size())).getId();
		  }

		  if(winnerEmployeeId != 0) {
          	setWinnerEmployeeByMonth(winnerEmployeeId);
          }
		return winnerEmployeeId;
	}
	
	public void setWinnerEmployeeByMonth(int employeeId){
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		String monthOfYearStr = String.valueOf(month);

		if(!isWinnerSavedForMonth(monthOfYearStr)) {
			DrawPrize drawPrize = new DrawPrize();
			drawPrize.setEmployeeId(employeeId);
			drawPrize.setCreateDate(new Date());
			drawPrize.setMonthByYear(month);
			
			drawPrizeRepository.save(drawPrize);
		}
	}
	
	public boolean isWinnerSavedForMonth(String month) {
		boolean isThereRecord = false;
		Employee employee;
		
		employee = drawPrizeService.getWinnerEmployeeByMonth(month);
		
		if(employee.getId() != 0) {
			isThereRecord = true;
		}
		return isThereRecord;
	}
}
