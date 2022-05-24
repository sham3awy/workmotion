package com.workmotion.senior.assignment.services;

import com.workmotion.senior.assignment.models.dto.EmployeeResponse;
import com.workmotion.senior.assignment.models.orm.Employee;

public interface EmployeeService {

	EmployeeResponse addEmployee(Employee employee);

	EmployeeResponse fetchEmployeeDetails(Long id);

	EmployeeResponse changeState(Long id, String state);
}
