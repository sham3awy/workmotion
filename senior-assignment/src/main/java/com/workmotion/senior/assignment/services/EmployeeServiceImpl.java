package com.workmotion.senior.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.webjars.NotFoundException;
import com.workmotion.senior.assignment.models.dto.EmployeeResponse;
import com.workmotion.senior.assignment.models.orm.Employee;
import com.workmotion.senior.assignment.models.orm.State;
import com.workmotion.senior.assignment.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	private StateMachineEmployee stateMachineEmployee = new StateMachineEmployee();

	@Override
	public EmployeeResponse addEmployee(Employee employee) {
		EmployeeResponse employeeResponse = new EmployeeResponse();
		try {
			employee.setState(new State());
			employeeResponse.setEmployee(employeeRepository.save(employee));
			return employeeResponse;
		} catch (Exception e) {
			employeeResponse.setCode(HttpStatus.NOT_FOUND.toString());
			employeeResponse.setMessageType(HttpStatus.NOT_FOUND.getReasonPhrase());
			employeeResponse.setErrorMessageContent("Could not add employee.");
		}
		return employeeResponse;
	}
	
	@Override
	@ResponseBody
	public EmployeeResponse fetchEmployeeDetails(Long id) {
		EmployeeResponse employee = new EmployeeResponse();
		try {
			employee.setEmployee(employeeRepository.findById(id).get());
		} catch (Exception e) {
			employee.setCode(HttpStatus.NOT_FOUND.toString());
			employee.setMessageType(HttpStatus.NOT_FOUND.getReasonPhrase());
			employee.setErrorMessageContent("Employee with requested id not found");
		}
		return employee;
	}

	@Override
	public EmployeeResponse changeState(Long id, String state) {
		EmployeeResponse employee = new EmployeeResponse();
		try {
			Employee emp = fetchEmployeeDetails(id).getEmployee();
			if (emp == null ) {
		        throw new NotFoundException("Employee with requested id not found");
			}
			emp.setState(stateMachineEmployee.changeState(emp.getState(), state));
			employeeRepository.save(emp);
			employee.setEmployee(emp);
		} catch (NotFoundException e) {
			employee.setCode(HttpStatus.NOT_FOUND.toString());
			employee.setMessageType(HttpStatus.NOT_FOUND.getReasonPhrase());
			employee.setErrorMessageContent("Employee with requested id not found");
		}
		catch (Exception e) {
			employee.setCode(HttpStatus.METHOD_NOT_ALLOWED.toString());
			employee.setMessageType(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
			employee.setErrorMessageContent(e.getMessage());
		}
		return employee;
	}
}