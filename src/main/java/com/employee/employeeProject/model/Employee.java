package com.employee.employeeProject.model;

import java.time.LocalDate;
import java.util.Date;

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
public class Employee {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String surname;
	private String departmentId;
	private String location;
	private LocalDate startDate;
}
