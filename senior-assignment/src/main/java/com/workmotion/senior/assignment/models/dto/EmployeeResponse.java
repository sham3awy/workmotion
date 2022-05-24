package com.workmotion.senior.assignment.models.dto;

import com.workmotion.senior.assignment.models.orm.Employee;

public class EmployeeResponse extends BaseResponse {

	private Employee employee;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}