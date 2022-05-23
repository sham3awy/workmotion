package com.workmotion.senior.assignment.services;

import com.workmotion.senior.assignment.entities.Employee;

public interface EmployeeService {

	Employee createEmployee(Employee employee);

	Employee fetchEmployeeDetails(Long id);

	Employee changeState(Long id, String state) throws Exception;
}
