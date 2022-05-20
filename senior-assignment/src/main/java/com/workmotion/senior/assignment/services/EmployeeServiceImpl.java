package com.workmotion.senior.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workmotion.senior.assignment.entities.Employee;
import com.workmotion.senior.assignment.enums.State;
import com.workmotion.senior.assignment.repositories.EmployeeRepository;
import com.workmotion.senior.assignment.responses.Response;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		employee.setState(State.ADDED.getState());
		return employeeRepository.save(employee);
	}

	@Override
	@ResponseBody
	public Employee fetchEmployeeDetails(Long employeeId) {
		return employeeRepository.findById(employeeId).get();
	}

	@Override
	public Employee updateEmployee(Employee employee, String nextState) throws Exception {
		if (employee.getState().equals(State.ADDED.getState()) && !nextState.equals(State.IN_CHECK.getState())) {
//			return new Response("Transition Not Allowed");
			throw new Exception("Transition Not Allowed");
		}
		
		return employeeRepository.save(employee);
	}
}
