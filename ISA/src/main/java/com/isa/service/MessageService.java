package com.isa.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.isa.entity.User;

@Service
public class MessageService {

	@Autowired
	JavaMailSender javaMailSender;

	public void sendMessage(User user){
		SimpleMailMessage mail = new SimpleMailMessage();//spring.mail.properties.mail.smtp.ssl.trust = smtp.gmail.com
		mail.setTo("restaurant.reservation.team@gmail.com");
		mail.setFrom("restaurant.reservation.team@gmail.com");
		mail.setSubject("Activation link");
		Random rand = new Random();
		int  n = rand.nextInt(10000) + 99999;
		String code = Integer.toString(n);
		String idString = Long.toString(user.getId());
		code += idString;
		mail.setText("Please click on link to confirm your registration: http://localhost:6969/#!/guests/confirmation/" + code);//od prvih pet cifara random br, ostale id quest-a
		javaMailSender.send(mail);
	}
	
	public void sendInvitation(User u,User friend,Long reservationId){
		SimpleMailMessage mail = new SimpleMailMessage();//spring.mail.properties.mail.smtp.ssl.trust = smtp.gmail.com
		//mail.setTo(friend.getEmail());
		mail.setTo("restaurant.reservation.team@gmail.com");

		mail.setFrom("restaurant.reservation.team@gmail.com");
		mail.setSubject("Activation link");
		Random rand = new Random();
		int  n = rand.nextInt(10000) + 99999;
		String code = Integer.toString(n);
		String idString = Long.toString(reservationId);
		code += idString;
		int  n2 = rand.nextInt(10000) + 99999;
		String code2 = Integer.toString(n2);
		String idString2 = Long.toString(friend.getId());
		code2 += idString2;
		mail.setText(u.getFirstName()+" "+u.getLastName() +" invited you to join him, see details: http://localhost:6969/#!/reservations/confirmation/" + code+"/" + code2);//od prvih pet cifara random br, ostale id rezervacije
		javaMailSender.send(mail);		
	}
}
