package com.employee.employeeProject.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.employee.employeeProject.dao.DrawPrizeRepository;
import com.employee.employeeProject.model.DrawPrize;
import com.employee.employeeProject.model.Employee;
import com.employee.employeeProject.util.H2JDBCUtils;

@Service
public class DrawPrizeServiceImpl implements DrawPrizeService {
	
	@Autowired
	  private DrawPrizeRepository drawPrizeRepository;
	  @Override
	  public DrawPrize addDrawPrize(DrawPrize drawPrize) {
	    return drawPrizeRepository.save(drawPrize);
	  }
	  @Override
	  public DrawPrize getDrawPrizeById(int drawPrizeId) {
	    return drawPrizeRepository.findById(drawPrizeId).get();
	  }
	  @Override
	  public List<DrawPrize> getAllDrawPrize(){
	    return drawPrizeRepository.findAll();
	  }
	  
	  @Override
	  public void updateDrawPrize(DrawPrize drawPrize) {
	    // check if the user with the passed id exists or not
		  DrawPrize drawPrizeDB = drawPrizeRepository.findById(drawPrize.getId()).orElseThrow();
	    //TODO: If user exist drawPrize then updated
		  drawPrizeRepository.save(drawPrize);
	  }
	  
	  @Override
	  public void deleteDrawPrizeById(int drawPrizeId) {
	    try {
	    	drawPrizeRepository.deleteById(drawPrizeId);  
	    }catch(DataAccessException ex){
	      throw new RuntimeException(ex.getMessage());
	    }
	  }
	  
	  @Override
	  public Employee getWinnerEmployeeByMonth(String monthByYear) {

		  Employee employee = new Employee();
		  String query = "select e.name, e.surname from employee e, draw_Prize d where e.id = d.employee_id and d.month_By_Year = ? ";

		        try (Connection connection = H2JDBCUtils.getConnection();

		            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
		            preparedStatement.setString(1, monthByYear);

		            ResultSet rs = preparedStatement.executeQuery();

		            while (rs.next()) {
		            	employee.setName(rs.getString("name"));
		            	employee.setSurname(rs.getString("surname"));
		            }
		        } catch (SQLException e) {
		            H2JDBCUtils.printSQLException(e);
		        }
		   return employee;
	  
	  }
}
