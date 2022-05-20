package com.workmotion.senior.assignment.services;

import com.workmotion.senior.assignment.entities.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	Employee fetchEmployeeDetails(Long employeeId);

	Employee updateEmployee(Employee employee, String state);
}
