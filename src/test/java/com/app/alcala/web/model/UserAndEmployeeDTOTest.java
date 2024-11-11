package com.app.alcala.web.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.app.alcala.entities.Employee;
import com.app.alcala.entities.User;

public class UserAndEmployeeDTOTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        UserAndEmployeeDTO dto = new UserAndEmployeeDTO();
        assertThat(dto).isNotNull();

        // Verifica el constructor con todos los argumentos
        User user = new User("username1", "password1");  // Ajusta el constructor de acuerdo a tu clase User
        Employee employee = new Employee();  // Ajusta el constructor de acuerdo a tu clase Employee
        UserAndEmployeeDTO dtoWithValues = new UserAndEmployeeDTO(user, employee);

        assertThat(dtoWithValues.getUser()).isEqualTo(user);
        assertThat(dtoWithValues.getEmployee()).isEqualTo(employee);
    }

    @Test
    public void testGettersSetters() {
        UserAndEmployeeDTO dto = new UserAndEmployeeDTO();
        
        User user = new User("username1", "password1");  // Ajusta el constructor de acuerdo a tu clase User
        Employee employee = new Employee();  // Ajusta el constructor de acuerdo a tu clase Employee
        dto.setUser(user);
        dto.setEmployee(employee);

        assertThat(dto.getUser()).isEqualTo(user);
        assertThat(dto.getEmployee()).isEqualTo(employee);
    }

    @Test
    public void testEqualsHashCode() {
        User user = new User("username1", "password1");  // Ajusta el constructor de acuerdo a tu clase User
        Employee employee = new Employee();  // Ajusta el constructor de acuerdo a tu clase Employee

        UserAndEmployeeDTO dto1 = new UserAndEmployeeDTO(user, employee);
        UserAndEmployeeDTO dto2 = new UserAndEmployeeDTO(user, employee);

        // Verifica que dos objetos con los mismos datos sean iguales y tengan el mismo hashCode
        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    public void testToString() {
        User user = new User("username1", "password1");  // Ajusta el constructor de acuerdo a tu clase User
        Employee employee = new Employee();  // Ajusta el constructor de acuerdo a tu clase Employee

        UserAndEmployeeDTO dto = new UserAndEmployeeDTO(user, employee);
        String esperado = "UserAndEmployeeDTO(user=" + user.toString() + ", employee=" + employee.toString() + ")";

        // Verifica que el método toString produce la salida esperada
        assertThat(dto.toString()).isEqualTo(esperado);
    }
}
