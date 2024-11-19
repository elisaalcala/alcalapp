package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReleaseTest {

    private Release release;
    private Timestamp initialDate;
    private Timestamp requirementsDate;
    private Timestamp prjAssignmentDate;
    private Timestamp developDate;
    private Timestamp tstDate;
    private Timestamp uatDate;
    private Timestamp proDate;
    private Map<Long, Project> projectMap;

    @BeforeEach
    public void setup() {
        initialDate = Timestamp.valueOf("2023-01-01 10:00:00");
        requirementsDate = Timestamp.valueOf("2023-01-05 10:00:00");
        prjAssignmentDate = Timestamp.valueOf("2023-01-10 10:00:00");
        developDate = Timestamp.valueOf("2023-02-01 10:00:00");
        tstDate = Timestamp.valueOf("2023-02-10 10:00:00");
        uatDate = Timestamp.valueOf("2023-02-15 10:00:00");
        proDate = Timestamp.valueOf("2023-03-01 10:00:00");

        projectMap = new HashMap<>();
        
        release = new Release(
                1L,
                "Release A",
                initialDate,
                requirementsDate,
                prjAssignmentDate,
                developDate,
                tstDate,
                uatDate,
                proDate,
                "InProgress",
                "john.doe",
                projectMap,
                null
        );
    }

    @Test
    public void testConstructor() {
        // Verifica que los valores se inicialicen correctamente
        assertThat(release.getIdRelease()).isEqualTo(1L);
        assertThat(release.getNameRelease()).isEqualTo("Release A");
        assertThat(release.getInitialDate()).isEqualTo(initialDate);
        assertThat(release.getRequirementsDate()).isEqualTo(requirementsDate);
        assertThat(release.getPrjAssignmentDate()).isEqualTo(prjAssignmentDate);
        assertThat(release.getDevelopDate()).isEqualTo(developDate);
        assertThat(release.getTstDate()).isEqualTo(tstDate);
        assertThat(release.getUatDate()).isEqualTo(uatDate);
        assertThat(release.getProDate()).isEqualTo(proDate);
        assertThat(release.getStatusRelease()).isEqualTo("InProgress");
        assertThat(release.getEmployeeUserCreation()).isEqualTo("john.doe");
        assertThat(release.getProjectMap()).isEqualTo(projectMap);
        assertThat(release.getEmployeeCreation()).isNull();
    }

    @Test
    public void testGettersSetters() {
        Release newRelease = new Release();

        // Setea valores utilizando setters
        newRelease.setIdRelease(2L);
        newRelease.setNameRelease("Release B");
        newRelease.setInitialDate(Timestamp.valueOf("2024-01-01 10:00:00"));
        newRelease.setRequirementsDate(Timestamp.valueOf("2024-01-05 10:00:00"));
        newRelease.setPrjAssignmentDate(Timestamp.valueOf("2024-01-10 10:00:00"));
        newRelease.setDevelopDate(Timestamp.valueOf("2024-02-01 10:00:00"));
        newRelease.setTstDate(Timestamp.valueOf("2024-02-10 10:00:00"));
        newRelease.setUatDate(Timestamp.valueOf("2024-02-15 10:00:00"));
        newRelease.setProDate(Timestamp.valueOf("2024-03-01 10:00:00"));
        newRelease.setStatusRelease("Completed");
        newRelease.setEmployeeUserCreation("alice.doe");

        // Verifica los valores establecidos
        assertThat(newRelease.getIdRelease()).isEqualTo(2L);
        assertThat(newRelease.getNameRelease()).isEqualTo("Release B");
        assertThat(newRelease.getInitialDate()).isEqualTo(Timestamp.valueOf("2024-01-01 10:00:00"));
        assertThat(newRelease.getRequirementsDate()).isEqualTo(Timestamp.valueOf("2024-01-05 10:00:00"));
        assertThat(newRelease.getPrjAssignmentDate()).isEqualTo(Timestamp.valueOf("2024-01-10 10:00:00"));
        assertThat(newRelease.getDevelopDate()).isEqualTo(Timestamp.valueOf("2024-02-01 10:00:00"));
        assertThat(newRelease.getTstDate()).isEqualTo(Timestamp.valueOf("2024-02-10 10:00:00"));
        assertThat(newRelease.getUatDate()).isEqualTo(Timestamp.valueOf("2024-02-15 10:00:00"));
        assertThat(newRelease.getProDate()).isEqualTo(Timestamp.valueOf("2024-03-01 10:00:00"));
        assertThat(newRelease.getStatusRelease()).isEqualTo("Completed");
        assertThat(newRelease.getEmployeeUserCreation()).isEqualTo("alice.doe");
    }

    @Test
    public void testEqualsAndHashCode() {
        Release anotherRelease = new Release(
                1L,
                "Release A",
                initialDate,
                requirementsDate,
                prjAssignmentDate,
                developDate,
                tstDate,
                uatDate,
                proDate,
                "InProgress",
                "john.doe",
                projectMap,
                null
        );

        // Verifica que dos objetos con los mismos valores sean iguales
        assertThat(release).isEqualTo(anotherRelease);
        assertThat(release.hashCode()).isEqualTo(anotherRelease.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Release(idRelease=1, nameRelease=Release A, initialDate=" + initialDate.toString() + 
                ", requirementsDate=" + requirementsDate.toString() + ", prjAssignmentDate=" + prjAssignmentDate.toString() +
                ", developDate=" + developDate.toString() + ", tstDate=" + tstDate.toString() + ", uatDate=" + uatDate.toString() +
                ", proDate=" + proDate.toString() + ", statusRelease=InProgress, employeeUserCreation=john.doe, " +
                 "employeeCreation=null)";

        // Verifica que el método toString genere la salida esperada
        assertThat(release.toString()).isEqualTo(expectedString);
    }
}
