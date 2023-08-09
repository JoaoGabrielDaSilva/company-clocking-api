package com.ponto.api.controller;

import com.ponto.api.dto.EmployeeDTO;
import com.ponto.api.dto.EmployeeListResponse;
import com.ponto.api.model.Employee;
import com.ponto.api.repository.EmployeeRepository;
import com.ponto.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public ResponseEntity<EmployeeListResponse> getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        EmployeeListResponse employeeListResponse = employeeService.getAllEmployees(page, pageSize);

        return new ResponseEntity<>(employeeListResponse, HttpStatus.OK);
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable String id) {

        EmployeeDTO employee = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    @PostMapping("employee/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO data) {
        EmployeeDTO employee = this.employeeService.createEmployee(data);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @PutMapping("employee/{id}/update")
    public ResponseEntity<EmployeeDTO> updateEmployee( @RequestBody EmployeeDTO employeeDTO, @PathVariable("id") String employeeId) {

        EmployeeDTO updatedEmployee = this.employeeService.updateEmployee(employeeDTO, employeeId);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("employee/{id}/delete")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
    }
}
