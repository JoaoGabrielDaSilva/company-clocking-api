package com.ponto.api.service.impl;

import com.ponto.api.dto.EmployeeDTO;
import com.ponto.api.dto.EmployeeListResponse;
import com.ponto.api.exception.EmployeeNotFoundException;
import com.ponto.api.model.Employee;
import com.ponto.api.repository.EmployeeRepository;
import com.ponto.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PageRanges;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public EmployeeListResponse getAllEmployees(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Employee> employees = this.employeeRepository.findAll(pageable);
        List<Employee> listOfEmployee = employees.getContent();

        List<EmployeeDTO> content = listOfEmployee.stream().map(EmployeeServiceImpl::mapToDTO).toList();

        EmployeeListResponse response = new EmployeeListResponse();
        response.setContent(content);
        response.setPage(employees.getNumber());
        response.setPageSize(employees.getSize());
        response.setTotalElements(employees.getTotalElements());
        response.setTotalPages(employees.getTotalPages());
        response.setLast(employees.isLast());

        return response;
    }

    public EmployeeDTO getEmployeeById(String id) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee could not be found"));

        return mapToDTO(employee);
    }
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        Employee newEmployee = this.employeeRepository.save(employee);

        EmployeeDTO employeeResponse = new EmployeeDTO();
        employeeResponse.setId(newEmployee.getId());
        employeeResponse.setName(newEmployee.getName());
        employeeResponse.setEmail(newEmployee.getEmail());

        return employeeResponse;
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee could not be updated"));

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee could not be deleted"));

        employeeRepository.delete(employee);
    }

    private static EmployeeDTO mapToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());

        return employeeDTO;
    }

    private static Employee mapToEntity(Employee employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());

        return employee;
    }
}
