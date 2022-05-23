package com.workmotion.senior.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.workmotion.senior.assignment.entities.Employee;
import com.workmotion.senior.assignment.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.createEmployee(employee));
	}

	@GetMapping("/get")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Employee> fetchEmployeeDetails(@RequestParam("id") Long id){
		return ResponseEntity.ok(employeeService.fetchEmployeeDetails(id));
	}

	@PutMapping("/change-state")
	public ResponseEntity<Employee> changeEmployeeState(@RequestParam("id") Long id, @RequestParam("state") String state) throws Exception {
		return ResponseEntity.ok(employeeService.changeState(id, state));
	}
}
