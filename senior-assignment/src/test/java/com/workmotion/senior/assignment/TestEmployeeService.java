package com.workmotion.senior.assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.workmotion.senior.assignment.enums.StateEnum;
import com.workmotion.senior.assignment.models.dto.EmployeeResponse;
import com.workmotion.senior.assignment.models.orm.Employee;
import com.workmotion.senior.assignment.services.EmployeeServiceImpl;

public class TestEmployeeService {

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    Employee employee;
    EmployeeResponse employeeResponse;

    @BeforeEach
    public void before() {
        employee = new Employee();
        employee.setName("Mohamed");
        employee.setMobile("0556489014");
        employee.setEmail("mohamedabosham3a@yahoo.com");
        employeeResponse = employeeService.addEmployee(employee);
    }

    @Test
    public void createEmployee(){
        Employee employee = new Employee();
        employee.setName("Mohamed");
        employee.setMobile("0556489014");
        employee.setEmail("mohamedabosham3a@yahoo.com");
        employeeResponse = employeeService.addEmployee(employee);
        Assertions.assertEquals(employeeResponse.getEmployee().getId(), 2L);
        Assertions.assertEquals(employeeResponse.getEmployee().getName(), "Mohamed");
        Assertions.assertEquals(employeeResponse.getEmployee().getMobile(), "0556489014");
        Assertions.assertEquals(employeeResponse.getEmployee().getEmail(), "mohamedabosham3a@yahoo.com");
    }

    @Test
    public void changeState() {
        employeeService.changeState(1L, "IN_CHECK");
        Assertions.assertEquals(employee.getState(), StateEnum.IN_CHECK);
        employeeService.changeState(1L, "SECURITY_CHECK_FINISHED");
        Assertions.assertEquals(employee.getState(), StateEnum.SECURITY_CHECK_FINISHED);
        employeeService.changeState(1L, "WORK_PERMIT_CHECK_PENDING_VERIFICATION");
        Assertions.assertEquals(employee.getState(), StateEnum.WORK_PERMIT_CHECK_PENDING_VERIFICATION);
        employeeService.changeState(1L, "WORK_PERMIT_CHECK_FINISHED");
        Assertions.assertEquals(employee.getState(), StateEnum.WORK_PERMIT_CHECK_FINISHED);
        employeeService.changeState(1L, "ACTIVE");
        Assertions.assertEquals(employee.getState(), StateEnum.ACTIVE);
    }

    @Test
    public void changeStateInvalid() {
        try {
            employeeService.changeState(1L, "IN_CHECK");
            Assertions.assertEquals(employee.getState(), StateEnum.IN_CHECK);
            employeeService.changeState(1L, "ACTIVE");
            Assertions.fail("The expected exception wasn't thrown.");
        }
        catch(IllegalStateException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("No valid leaving transitions are permitted from state 'IN_CHECK' for trigger 'ACTIVE'. Consider ignoring the trigger.",e.getMessage());
        }
    }

    @Test
    public void getEmployee() {
    	EmployeeResponse employee = employeeService.fetchEmployeeDetails(1L);
        Assertions.assertEquals(employee.getEmployee().getId(), 1L);
        Assertions.assertEquals(employee.getEmployee().getName(), "Mohamed");
        Assertions.assertEquals(employee.getEmployee().getMobile(), "0556489014");
        Assertions.assertEquals(employee.getEmployee().getEmail(), "mohamedabosham3a@yahoo.com");

    }

    @Test
    public void getEmployeeInvalid() {
        try {
             employeeService.fetchEmployeeDetails(100L);
             Assertions.fail("The expected exception wasn't thrown.");
        }
        catch(Exception e){
        Assertions.assertEquals("Employee with requested id not found", e.getMessage());
        }
    }
}
