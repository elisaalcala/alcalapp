package com.app.alcala.entities;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {

    private Message message;
    private Timestamp dateRecord;

    @BeforeEach
    public void setup() {
        // Inicializa un valor de fecha de prueba
        dateRecord = Timestamp.valueOf("2023-01-01 10:00:00");

        // Crea un objeto Message utilizando el constructor completo
        message = new Message(
                1L,
                "JohnDoe",
                "Hello, this is a test message.",
                dateRecord
        );
    }

    @Test
    public void testConstructor() {
        // Verifica que el constructor asigne correctamente los valores
        assertThat(message.getIdRecord()).isEqualTo(1L);
        assertThat(message.getUserName()).isEqualTo("JohnDoe");
        assertThat(message.getText()).isEqualTo("Hello, this is a test message.");
        assertThat(message.getDateRecord()).isEqualTo(dateRecord);
    }

    @Test
    public void testGettersSetters() {
        Message newMessage = new Message();

        // Asigna valores a los campos mediante los setters
        newMessage.setIdRecord(2L);
        newMessage.setUserName("JaneDoe");
        newMessage.setText("Another test message.");
        newMessage.setDateRecord(Timestamp.valueOf("2024-01-01 12:00:00"));

        // Verifica que los getters devuelvan los valores esperados
        assertThat(newMessage.getIdRecord()).isEqualTo(2L);
        assertThat(newMessage.getUserName()).isEqualTo("JaneDoe");
        assertThat(newMessage.getText()).isEqualTo("Another test message.");
        assertThat(newMessage.getDateRecord()).isEqualTo(Timestamp.valueOf("2024-01-01 12:00:00"));
    }

    @Test
    public void testEqualsAndHashCode() {
        Message anotherMessage = new Message(
                1L,
                "JohnDoe",
                "Hello, this is a test message.",
                dateRecord
        );

        // Verifica que dos objetos con los mismos valores sean iguales
        assertThat(message).isEqualTo(anotherMessage);
        assertThat(message.hashCode()).isEqualTo(anotherMessage.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Message(idRecord=1, userName=JohnDoe, text=Hello, this is a test message., dateRecord=" + dateRecord.toString() + ")";

        // Verifica que el método toString genere la salida esperada
        assertThat(message.toString()).isEqualTo(expectedString);
    }
}
