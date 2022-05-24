package com.workmotion.senior.assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.workmotion.senior.assignment.enums.StateEnum;
import com.workmotion.senior.assignment.models.dto.EmployeeResponse;
import com.workmotion.senior.assignment.models.orm.Employee;
import com.workmotion.senior.assignment.services.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SeniorAssignmentApplication.class)
@SpringBootTest
public class TestEmployeeService {

	@Autowired
	private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

	Employee employee;

	@BeforeEach
	public void before() {
		employee = new Employee();
		employee.setName("Mohamed");
		employee.setMobile("0556489014");
		employee.setEmail("mohamedabosham3a@yahoo.com");
		employee = employeeService.addEmployee(employee).getEmployee();
	}

	@Test
	public void createEmployee() {
		Employee employee = new Employee();
		employee.setName("Mohamed");
		employee.setMobile("0556489014");
		employee.setEmail("mohamedabosham3a@yahoo.com");
		employee = employeeService.addEmployee(employee).getEmployee();
		Assertions.assertEquals(employee.getName(), "Mohamed");
		Assertions.assertEquals(employee.getMobile(), "0556489014");
		Assertions.assertEquals(employee.getEmail(), "mohamedabosham3a@yahoo.com");
	}

	@Test
	public void changeState() {
		Employee employee = employeeService.fetchEmployeeDetails(1L).getEmployee();

		employee = employeeService.changeState(employee.getId(), "IN_CHECK").getEmployee();
		Assertions.assertEquals(employee.getState().getState(), StateEnum.WORK_PERMIT_CHECK_STARTED);

		employee = employeeService.changeState(employee.getId(), "SECURITY_CHECK_FINISHED").getEmployee();
		Assertions.assertEquals(employee.getState().getState(), StateEnum.SECURITY_CHECK_FINISHED);

		employee = employeeService.changeState(employee.getId(), "WORK_PERMIT_CHECK_PENDING_VERIFICATION")
				.getEmployee();
		Assertions.assertEquals(employee.getState().getState(), StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION);

		employee = employeeService.changeState(employee.getId(), "WORK_PERMIT_CHECK_FINISHED").getEmployee();
		Assertions.assertEquals(employee.getState().getState(), StateEnum.APPROVED);

		employee = employeeService.changeState(employee.getId(), "ACTIVE").getEmployee();
		Assertions.assertEquals(employee.getState().getState(), StateEnum.ACTIVE);
	}

	@Test
	public void changeStateInvalid() {
		
			EmployeeResponse employeeResponse = employeeService.fetchEmployeeDetails(2L);
			employeeResponse = employeeService.changeState(employeeResponse.getEmployee().getId(), "IN_CHECK");
			Assertions.assertEquals(employeeResponse.getEmployee().getState().getState(),
					StateEnum.WORK_PERMIT_CHECK_STARTED);
			employeeResponse = employeeService.changeState(employeeResponse.getEmployee().getId(), "ACTIVE");
			if (!employeeResponse.getCode().equals(HttpStatus.NOT_FOUND.toString())
					&& !employeeResponse.getCode().equals(HttpStatus.METHOD_NOT_ALLOWED.toString())) {
				Assertions.fail("The expected exception wasn't thrown.");
			} else {
				Assertions.assertEquals(
						"No valid leaving transitions are permitted from state 'WORK_PERMIT_CHECK_STARTED' for trigger 'ACTIVE'. Consider ignoring the trigger.",
						employeeResponse.getErrorMessageContent());
			}
		
	}

	@Test
	public void getEmployee() {
		Employee employee = employeeService.fetchEmployeeDetails(1L).getEmployee();
		Assertions.assertEquals(employee.getId(), 1);
		Assertions.assertEquals(employee.getName(), "Mohamed");
		Assertions.assertEquals(employee.getMobile(), "0556489014");
		Assertions.assertEquals(employee.getEmail(), "mohamedabosham3a@yahoo.com");
	}

	@Test
	public void getEmployeeInvalid() {
		EmployeeResponse employeeResponse = employeeService.fetchEmployeeDetails(100L);
		if (!employeeResponse.getCode().equals(HttpStatus.NOT_FOUND.toString())) {
			Assertions.fail("The expected exception wasn't thrown.");
		} else {
			Assertions.assertEquals("Employee with requested id not found", employeeResponse.getErrorMessageContent());
		}
	}
}
