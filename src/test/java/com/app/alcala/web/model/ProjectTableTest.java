package com.app.alcala.web.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.app.alcala.entities.Project;

public class ProjectTableTest {

    @Test
    public void testConstructores() {
        // Verifica el constructor sin argumentos
        ProjectTable projectTable = new ProjectTable();
        assertThat(projectTable).isNotNull();

        // Verifica el constructor con todos los argumentos
        List<Project> projects = new ArrayList<>(); // Crea una lista vacía o una lista con proyectos de prueba
        String releaseName = "Release 1.0";
        Long idRelease = 1L;
        Timestamp initialDate = Timestamp.valueOf("2024-01-01 00:00:00");
        Timestamp developDate = Timestamp.valueOf("2024-02-01 00:00:00");
        Timestamp tstDate = Timestamp.valueOf("2024-03-01 00:00:00");
        Timestamp uatDate = Timestamp.valueOf("2024-04-01 00:00:00");
        Timestamp proDate = Timestamp.valueOf("2024-05-01 00:00:00");

        ProjectTable projectTableConDatos = new ProjectTable(projects, releaseName, idRelease, initialDate, developDate, tstDate, uatDate, proDate);

        assertThat(projectTableConDatos.getProjects()).isEqualTo(projects);
        assertThat(projectTableConDatos.getReleaseName()).isEqualTo(releaseName);
        assertThat(projectTableConDatos.getIdRelease()).isEqualTo(idRelease);
        assertThat(projectTableConDatos.getInitialDate()).isEqualTo(initialDate);
        assertThat(projectTableConDatos.getDevelopDate()).isEqualTo(developDate);
        assertThat(projectTableConDatos.getTstDate()).isEqualTo(tstDate);
        assertThat(projectTableConDatos.getUatDate()).isEqualTo(uatDate);
        assertThat(projectTableConDatos.getProDate()).isEqualTo(proDate);
    }

    @Test
    public void testGettersSetters() {
        ProjectTable projectTable = new ProjectTable();

        List<Project> projects = new ArrayList<>(); // Lista de proyectos vacía o de prueba
        projectTable.setProjects(projects);
        projectTable.setReleaseName("Release 2.0");
        projectTable.setIdRelease(2L);
        projectTable.setInitialDate(Timestamp.valueOf("2025-01-01 00:00:00"));
        projectTable.setDevelopDate(Timestamp.valueOf("2025-02-01 00:00:00"));
        projectTable.setTstDate(Timestamp.valueOf("2025-03-01 00:00:00"));
        projectTable.setUatDate(Timestamp.valueOf("2025-04-01 00:00:00"));
        projectTable.setProDate(Timestamp.valueOf("2025-05-01 00:00:00"));

        assertThat(projectTable.getProjects()).isEqualTo(projects);
        assertThat(projectTable.getReleaseName()).isEqualTo("Release 2.0");
        assertThat(projectTable.getIdRelease()).isEqualTo(2L);
        assertThat(projectTable.getInitialDate()).isEqualTo(Timestamp.valueOf("2025-01-01 00:00:00"));
        assertThat(projectTable.getDevelopDate()).isEqualTo(Timestamp.valueOf("2025-02-01 00:00:00"));
        assertThat(projectTable.getTstDate()).isEqualTo(Timestamp.valueOf("2025-03-01 00:00:00"));
        assertThat(projectTable.getUatDate()).isEqualTo(Timestamp.valueOf("2025-04-01 00:00:00"));
        assertThat(projectTable.getProDate()).isEqualTo(Timestamp.valueOf("2025-05-01 00:00:00"));
    }

    @Test
    public void testEqualsHashCode() {
        List<Project> projects = new ArrayList<>(); // Lista vacía o de prueba
        String releaseName = "Release 1.0";
        Long idRelease = 1L;
        Timestamp initialDate = Timestamp.valueOf("2024-01-01 00:00:00");
        Timestamp developDate = Timestamp.valueOf("2024-02-01 00:00:00");
        Timestamp tstDate = Timestamp.valueOf("2024-03-01 00:00:00");
        Timestamp uatDate = Timestamp.valueOf("2024-04-01 00:00:00");
        Timestamp proDate = Timestamp.valueOf("2024-05-01 00:00:00");

        ProjectTable projectTable1 = new ProjectTable(projects, releaseName, idRelease, initialDate, developDate, tstDate, uatDate, proDate);
        ProjectTable projectTable2 = new ProjectTable(projects, releaseName, idRelease, initialDate, developDate, tstDate, uatDate, proDate);

        assertThat(projectTable1).isEqualTo(projectTable2);
        assertThat(projectTable1.hashCode()).isEqualTo(projectTable2.hashCode());
    }

    @Test
    public void testToString() {
        List<Project> projects = new ArrayList<>(); // Lista vacía o de prueba
        String releaseName = "Release 1.0";
        Long idRelease = 1L;
        Timestamp initialDate = Timestamp.valueOf("2024-01-01 00:00:00");
        Timestamp developDate = Timestamp.valueOf("2024-02-01 00:00:00");
        Timestamp tstDate = Timestamp.valueOf("2024-03-01 00:00:00");
        Timestamp uatDate = Timestamp.valueOf("2024-04-01 00:00:00");
        Timestamp proDate = Timestamp.valueOf("2024-05-01 00:00:00");

        ProjectTable projectTable = new ProjectTable(projects, releaseName, idRelease, initialDate, developDate, tstDate, uatDate, proDate);
        String esperado = "ProjectTable(projects=" + projects + ", releaseName=Release 1.0, idRelease=1, initialDate=2024-01-01 00:00:00.0, developDate=2024-02-01 00:00:00.0, tstDate=2024-03-01 00:00:00.0, uatDate=2024-04-01 00:00:00.0, proDate=2024-05-01 00:00:00.0)";

        assertThat(projectTable.toString()).isEqualTo(esperado);
    }
}
