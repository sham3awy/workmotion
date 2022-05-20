package com.workmotion.senior.assignment.services;

import com.workmotion.senior.assignment.entities.Employee;
import com.workmotion.senior.assignment.responses.Response;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	Employee fetchEmployeeDetails(Long employeeId);

	Employee updateEmployee(Employee employee, String state) throws Exception;
}
