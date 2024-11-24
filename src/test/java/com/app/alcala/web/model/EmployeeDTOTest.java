package com.app.alcala.web.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDTOTest {

    private EmployeeDTO employeeDTO;

    private Timestamp birthDate;

    @BeforeEach
    void setUp() {
        birthDate = new Timestamp(System.currentTimeMillis());
        // Inicializamos EmployeeDTO con algunos datos
        employeeDTO = new EmployeeDTO("jdoe", "John", "Doe", "12345678A", "Developer", birthDate, "Development", "Admin");
    }

    @Test
    void testConstructor() {
        // Verificamos que los datos fueron correctamente asignados
        assertNotNull(employeeDTO);
        assertEquals("jdoe", employeeDTO.getUsername());
        assertEquals("John", employeeDTO.getEmployeeName());
        assertEquals("Doe", employeeDTO.getEmployeeLastName());
        assertEquals("12345678A", employeeDTO.getEmployeeDni());
        assertEquals("Developer", employeeDTO.getEmployeePosition());
        assertEquals(birthDate, employeeDTO.getBirthDate());
        assertEquals("Development", employeeDTO.getNameTeam());
        assertEquals("Admin", employeeDTO.getRole());
    }

    @Test
    void testSettersAndGetters() {
        // Verificamos los métodos setters y getters generados por Lombok
        EmployeeDTO dto = new EmployeeDTO();
        dto.setUsername("mjane");
        dto.setEmployeeName("Mary");
        dto.setEmployeeLastName("Jane");
        dto.setEmployeeDni("87654321B");
        dto.setEmployeePosition("Manager");
        dto.setBirthDate(birthDate);
        dto.setNameTeam("Operations");
        dto.setRole("User");

        assertEquals("mjane", dto.getUsername());
        assertEquals("Mary", dto.getEmployeeName());
        assertEquals("Jane", dto.getEmployeeLastName());
        assertEquals("87654321B", dto.getEmployeeDni());
        assertEquals("Manager", dto.getEmployeePosition());
        assertEquals(birthDate, dto.getBirthDate());
        assertEquals("Operations", dto.getNameTeam());
        assertEquals("User", dto.getRole());
    }

    @Test
    void testNoArgsConstructor() {
        // Verificamos que el constructor sin argumentos funciona correctamente
        EmployeeDTO dto = new EmployeeDTO();
        assertNotNull(dto);
        assertNull(dto.getUsername());
        assertNull(dto.getEmployeeName());
        assertNull(dto.getEmployeeLastName());
        assertNull(dto.getEmployeeDni());
        assertNull(dto.getEmployeePosition());
        assertNull(dto.getBirthDate());
        assertNull(dto.getNameTeam());
        assertNull(dto.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        // Verificamos si los métodos equals y hashCode funcionan correctamente (Lombok genera estos métodos)
        EmployeeDTO dto1 = new EmployeeDTO("jdoe", "John", "Doe", "12345678A", "Developer", birthDate, "Development", "Admin");
        EmployeeDTO dto2 = new EmployeeDTO("jdoe", "John", "Doe", "12345678A", "Developer", birthDate, "Development", "Admin");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        // Cambiar un atributo y verificar que no son iguales
        dto2.setUsername("mjane");
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testToString() {
        // Verificamos el método toString generado por Lombok
        EmployeeDTO dto = new EmployeeDTO("jdoe", "John", "Doe", "12345678A", "Developer", birthDate, "Development", "Admin");
        String expectedToString = "EmployeeDTO(username=jdoe, employeeName=John, employeeLastName=Doe, employeeDni=12345678A, " +
                "employeePosition=Developer, birthDate=" + birthDate + ", nameTeam=Development, role=Admin)";
        
        assertEquals(expectedToString, dto.toString());
    }
}
