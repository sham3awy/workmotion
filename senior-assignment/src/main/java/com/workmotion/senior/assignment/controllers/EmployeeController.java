package com.workmotion.senior.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workmotion.senior.assignment.entities.Employee;
import com.workmotion.senior.assignment.responses.Response;
import com.workmotion.senior.assignment.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping("/employees")
	public Employee fetchEmployeeDetails(@RequestParam("id") Long employeeId) {
		return employeeService.fetchEmployeeDetails(employeeId);
	}

	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee, @RequestParam("state") String state) throws Exception {
		return employeeService.updateEmployee(employee, state);
	}
}
