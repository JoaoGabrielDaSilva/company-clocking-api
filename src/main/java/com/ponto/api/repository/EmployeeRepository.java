package com.ponto.api.repository;

import com.ponto.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  EmployeeRepository  extends JpaRepository<Employee, String> {

}
