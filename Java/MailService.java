package com.developdestroyer.taehobrary;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/* ------------------------------------------
 * Deverloper : Tae-ho Lee
 * E-mail : developdestroyer@gmail.com
 * Date : 2018-07-24
------------------------------------------- */

/* 해당 기능을 사용하기 위해서는 gradle 기준 의존성
 * compile ('org.springframework.boot:spring-boot-starter-mail') 를 추가하여야 합니다.
 * 본 메일기능의 경우 SMTP를 기반으로 기능을 수행 합니다.
 * 또한 properties 파일에 다음과 같이 정의하도록 합니다. 아래는 지메일 예시입니다.

# Mail Setting
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username={your email}@gmail.com
spring.mail.password={your pw}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000
 *
 */

@Component
public class MailService {

	@Autowired
	public JavaMailSender javaMailSender;

	public boolean sendMail(String to_email, String subject, String message) {

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);

		try {
			helper.setTo(to_email);
			helper.setSubject(subject);
			helper.setText(message, true);	//두번째 파라미터가 true면 html로 처리하며, 생략시 Text로만 간주 합니다.
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		javaMailSender.send(msg);

		return true;
	}
}