package com.workmotion.senior.assignment.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workmotion.senior.assignment.entities.Employee;
import com.workmotion.senior.assignment.entities.State;
import com.workmotion.senior.assignment.enums.StateEnum;
import com.workmotion.senior.assignment.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	private StateMachineEmployee stateMachineEmployee = new StateMachineEmployee();

	@Override
	public Employee createEmployee(Employee employee) {
		employee.setState(new State());
		return employeeRepository.save(employee);
	}

	@Override
	@ResponseBody
	public Employee fetchEmployeeDetails(Long id) {
		Optional<Employee> emp = Optional.ofNullable(employeeRepository.findById(id).get());
		if (emp.isPresent())
			return emp.get();
		throw new EntityNotFoundException("Employee with requested id not found");
	}

	@Override
	public Employee changeState(Long id, String state) {
		Employee employee = fetchEmployeeDetails(id);
		employee.setState(stateMachineEmployee.changeState(employee.getState(), state));
		return employeeRepository.save(employee);
	}
}