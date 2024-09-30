package com.app.alcala.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.alcala.entities.Message;
import com.app.alcala.repositories.MessageRepository;
import com.app.alcala.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageRepository messageRepository;
	
	public Message messageCreation(Timestamp date, String username, String teamName) {
		Message message = new Message();
		LocalDateTime truncatedDateTime = date.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(username);
		message.setText("El ticket ha sido creado por "+ teamName);
		return messageRepository.save(message);
	}
	
	@Override
	public Message messageAssign(Timestamp date, String userEmployee, String teamName) {
		Message message = new Message();
		LocalDateTime truncatedDateTime = date.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(userEmployee);
		message.setText("El ticket ha sido asignado a " + teamName);
		return messageRepository.save(message);
	}
	
	@Override
	public Message messageMove(Timestamp date, String userEmployee, String teamName) {
		Message message = new Message();
		LocalDateTime truncatedDateTime = date.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(userEmployee);
		message.setText("El ticket ha sido traspasado a " + teamName);
		return messageRepository.save(message);
	}
	
	
	@Override
	public Message messageFinish(Timestamp date, String userEmployee) {
		Message message = new Message();
		LocalDateTime truncatedDateTime = date.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(userEmployee);
		message.setText("El ticket ha sido resuelto");
		return messageRepository.save(message);
	}
	
	@Override
	public Message messageReOpen(Timestamp date, String userEmployee) {
		Message message = new Message();
		LocalDateTime truncatedDateTime = date.toLocalDateTime().withSecond(0).withNano(0);
	    Timestamp truncatedTimestamp = Timestamp.valueOf(truncatedDateTime);
		message.setDateRecord(truncatedTimestamp);
		message.setUserName(userEmployee);
		message.setText("El ticket ha sido reabierto");
		return messageRepository.save(message);
	}
	
}
