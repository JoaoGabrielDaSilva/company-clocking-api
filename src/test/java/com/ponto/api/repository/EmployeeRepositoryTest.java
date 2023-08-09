package com.ponto.api.repository;

import com.ponto.api.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void EmployeeRepository_Save_ReturnSavedEmployee(){
        Employee employee = new Employee().builder()
                .name("John Doe")
                .email("johndoe@gmail.com").build();


        Employee savedEmployee = employeeRepository.save(employee);

        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee).isEqualTo(employee);
    }
}
