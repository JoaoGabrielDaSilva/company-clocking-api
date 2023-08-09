package com.ponto.api.service;

import com.ponto.api.dto.EmployeeDTO;
import com.ponto.api.dto.EmployeeListResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeListResponse getAllEmployees(int pageNumber, int pageSize);

    EmployeeDTO getEmployeeById(String id);

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String id);

    void deleteEmployee(String id);

}
