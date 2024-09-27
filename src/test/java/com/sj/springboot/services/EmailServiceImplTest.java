package com.sj.springboot.services;

import com.sj.springboot.dto.Mail;
import com.sj.springboot.services.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    private Mail mail;

    @BeforeEach
    void setUp() {
        // Create a sample mail object
        mail = new Mail();
        mail.setRecipientsAddress("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setContent("Test Content");
    }

    @Test
    void sendMail_Success() {
        // Act
        emailService.sendMail(mail);

        // Assert: Create a SimpleMailMessage to match the expected sent message
        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setTo(mail.getRecipientsAddress());
        expectedMessage.setSubject(mail.getSubject());
        expectedMessage.setText(mail.getContent());
        expectedMessage.setFrom("samarth.dev@gmail.com"); // Default sender

        // Verify that JavaMailSender.send() was called with the correct message
        verify(mailSender, times(1)).send(Mockito.argThat((SimpleMailMessage message) ->
                message.getTo()[0].equals(mail.getRecipientsAddress()) &&
                        message.getSubject().equals(mail.getSubject()) &&
                        message.getText().equals(mail.getContent()) &&
                        message.getFrom().equals("samarth.dev@gmail.com")
        ));
    }

    @Test
    void sendMail_ExceptionHandling() {
        // Arrange: Simulate an exception being thrown by mailSender.send
        doThrow(new RuntimeException("Email sending failed")).when(mailSender).send(any(SimpleMailMessage.class));

        // Act: Call sendMail() and verify no exception is propagated
        emailService.sendMail(mail);

        // Assert: Ensure that mailSender.send() was called despite the exception
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
