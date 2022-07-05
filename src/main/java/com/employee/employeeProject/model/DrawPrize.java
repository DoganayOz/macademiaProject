package com.employee.employeeProject.model;

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
public class DrawPrize {
	@Id
	@GeneratedValue
	private int id;
	private int employeeId;
	private int monthByYear;
	private Date createDate;
}
