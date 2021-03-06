package com.workmotion.senior.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.workmotion.senior.assignment.models.orm.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
