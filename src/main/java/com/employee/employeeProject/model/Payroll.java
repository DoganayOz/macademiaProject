package com.employee.employeeProject.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payroll {
	@Id
	@GeneratedValue
	private int id;
	private int employeeId;
	private BigDecimal salary;
	private LocalDate payrollDate;
}
