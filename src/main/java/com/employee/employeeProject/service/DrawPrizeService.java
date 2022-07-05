package com.employee.employeeProject.service;

import java.util.List;

import com.employee.employeeProject.model.DrawPrize;
import com.employee.employeeProject.model.Employee;


public interface DrawPrizeService {
	DrawPrize addDrawPrize(DrawPrize drawPrize);
	DrawPrize getDrawPrizeById(int drawPrizeId);
	void updateDrawPrize(DrawPrize drawPrizeId);
	void deleteDrawPrizeById(int id);
	List<DrawPrize> getAllDrawPrize();
	Employee getWinnerEmployeeByMonth(String monthByYear);
}
