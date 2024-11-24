package com.app.alcala.web.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamDTOTest {

    private TeamDTO teamDTO;

    @BeforeEach
    void setUp() {
        // Inicializamos TeamDTO con algunos datos
        teamDTO = new TeamDTO("Development", "Team responsible for developing the product");
    }

    @Test
    void testConstructor() {
        // Verificamos que los datos fueron correctamente asignados
        assertNotNull(teamDTO);
        assertEquals("Development", teamDTO.getNameTeam());
        assertEquals("Team responsible for developing the product", teamDTO.getDescriptionTeam());
    }

    @Test
    void testSettersAndGetters() {
        // Verificamos los métodos setters y getters generados por Lombok
        TeamDTO dto = new TeamDTO();
        dto.setNameTeam("Operations");
        dto.setDescriptionTeam("Team handling operational tasks");

        assertEquals("Operations", dto.getNameTeam());
        assertEquals("Team handling operational tasks", dto.getDescriptionTeam());
    }

    @Test
    void testNoArgsConstructor() {
        // Verificamos que el constructor sin argumentos funciona correctamente
        TeamDTO dto = new TeamDTO();
        assertNotNull(dto);
        assertNull(dto.getNameTeam());
        assertNull(dto.getDescriptionTeam());
    }

    @Test
    void testEqualsAndHashCode() {
        // Verificamos si los métodos equals y hashCode funcionan correctamente (Lombok genera estos métodos)
        TeamDTO dto1 = new TeamDTO("Development", "Team responsible for developing the product");
        TeamDTO dto2 = new TeamDTO("Development", "Team responsible for developing the product");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());

        // Cambiar un atributo y verificar que no son iguales
        dto2.setNameTeam("Operations");
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testToString() {
        // Verificamos el método toString generado por Lombok
        TeamDTO dto = new TeamDTO("Development", "Team responsible for developing the product");
        String expectedToString = "TeamDTO(nameTeam=Development, descriptionTeam=Team responsible for developing the product)";
        
        assertEquals(expectedToString, dto.toString());
    }
}
