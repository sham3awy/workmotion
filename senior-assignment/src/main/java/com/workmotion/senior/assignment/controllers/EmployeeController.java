package com.workmotion.senior.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.workmotion.senior.assignment.models.dto.BaseResponse;
import com.workmotion.senior.assignment.models.dto.EmployeeResponse;
import com.workmotion.senior.assignment.models.orm.Employee;
import com.workmotion.senior.assignment.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/add")
	public BaseResponse addEmployee(@RequestBody Employee employee) {
		return this.employeeService.addEmployee(employee);
	}
	
	@GetMapping("/get")
	public EmployeeResponse fetchEmployeeDetails(@RequestParam("id") Long id) {
		return employeeService.fetchEmployeeDetails(id);
	}
	
	@PutMapping("/change-state")
	public BaseResponse changeEmployeeState(@RequestParam("id") Long id,
			@RequestParam("state") String state) {
		return employeeService.changeState(id, state);
	}
}