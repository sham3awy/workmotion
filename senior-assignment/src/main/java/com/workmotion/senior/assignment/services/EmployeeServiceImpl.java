package com.workmotion.senior.assignment.services;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workmotion.senior.assignment.entities.Employee;
import com.workmotion.senior.assignment.enums.State;
import com.workmotion.senior.assignment.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		employee.setState(State.ADDED.getState());
		return employeeRepository.save(employee);
	}

	@Override
	@ResponseBody
	public Employee fetchEmployeeDetails(Long employeeId) {
		return employeeRepository.findById(employeeId).get();
	}

	@Override
	public Employee updateEmployee(Employee employee, String state) {
		Employee savedEmployee = employeeRepository.findById(employee.getEmployeeId()).get();

		if (Objects.nonNull(employee.getName()) && !"".equalsIgnoreCase(employee.getName())) {
			savedEmployee.setName(employee.getName());
		}

		if (Objects.nonNull(employee.getMobile()) && !"".equalsIgnoreCase(employee.getMobile())) {
			savedEmployee.setMobile(employee.getMobile());
		}

		if (Objects.nonNull(employee.getState()) && !"".equalsIgnoreCase(employee.getState())) {
			savedEmployee.setState(employee.getState());
		}

		if (Objects.nonNull(employee.getAge())) {
			savedEmployee.setAge(employee.getAge());
		}

		return employeeRepository.save(savedEmployee);
	}
}
