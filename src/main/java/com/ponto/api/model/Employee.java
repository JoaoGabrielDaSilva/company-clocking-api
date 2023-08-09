package com.ponto.api.model;

import com.ponto.api.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "employees")
@Entity(name = "employees")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String email;

    public Employee(EmployeeDTO employee) {
        this.name = employee.getName();
        this.email = employee.getEmail();
    }
}
