package com.app.alcala.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.alcala.entities.Message;
import com.app.alcala.repositories.MessageRepository;


@ExtendWith(MockitoExtension.class)
public class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    @Test
    void testMessageCreation() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String username = "JohnDoe";
        String teamName = "Team A";

        Message message = new Message();
        LocalDateTime truncatedDateTime = timestamp.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(username);
        message.setText("Creado por " + teamName);

        when(messageRepository.save(message)).thenReturn(message);

        Message result = messageService.messageCreation(timestamp, username, teamName);

        assertEquals(message.getDateRecord(), result.getDateRecord());
        assertEquals(message.getUserName(), result.getUserName());
        assertEquals(message.getText(), result.getText());
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    void testMessageAssign() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userEmployee = "JohnDoe";
        String teamName = "Team A";

        Message message = new Message();
        LocalDateTime truncatedDateTime = timestamp.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
        message.setUserName(userEmployee);
        message.setText("Asignado a " + teamName);

        when(messageRepository.save(message)).thenReturn(message);

        Message result = messageService.messageAssign(timestamp, userEmployee, teamName);

        assertEquals(message.getDateRecord(), result.getDateRecord());
        assertEquals(message.getUserName(), result.getUserName());
        assertEquals(message.getText(), result.getText());
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    void testMessageMove() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userEmployee = "JohnDoe";
        String teamName = "Team B";

        Message message = new Message();
        LocalDateTime truncatedDateTime = timestamp.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(userEmployee);
        message.setText("Traspasado a " + teamName);

        when(messageRepository.save(message)).thenReturn(message);

        Message result = messageService.messageMove(timestamp, userEmployee, teamName);

        assertEquals(message.getDateRecord(), result.getDateRecord());
        assertEquals(message.getUserName(), result.getUserName());
        assertEquals(message.getText(), result.getText());
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    void testMessageFinish() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userEmployee = "JohnDoe";

        Message message = new Message();
        LocalDateTime truncatedDateTime = timestamp.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
        message.setUserName(userEmployee);
        message.setText("Resuelto");

        when(messageRepository.save(message)).thenReturn(message);

        Message result = messageService.messageFinish(timestamp, userEmployee);

        assertEquals(message.getDateRecord(), result.getDateRecord());
        assertEquals(message.getUserName(), result.getUserName());
        assertEquals(message.getText(), result.getText());
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    void testMessageReOpen() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String userEmployee = "JohnDoe";

        Message message = new Message();
        LocalDateTime truncatedDateTime = timestamp.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(userEmployee);
        message.setText("Reabierto");

        when(messageRepository.save(message)).thenReturn(message);

        Message result = messageService.messageReOpen(timestamp, userEmployee);

        assertEquals(message.getDateRecord(), result.getDateRecord());
        assertEquals(message.getUserName(), result.getUserName());
        assertEquals(message.getText(), result.getText());
        verify(messageRepository, times(1)).save(message);
    }
}
