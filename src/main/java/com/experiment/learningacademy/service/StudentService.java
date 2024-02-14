package com.experiment.learningacademy.service;

import com.experiment.learningacademy.model.Student;
import com.experiment.learningacademy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String registerStudent(Student student) {

        String toEmail = student.getEmail();
        String message1 = "hi Mr "+student.getFirstname() + " Thank you for registration. please confirm your registration by clicking below link ";




        student.setRegistrationConfirmation(false);
        this.studentRepository.save(student);




        String link = "http://localhost:8080/"+"confirm/"+student.getStudentId();
//        Util util = new Util();
//        util.sendMail(toEmail,message,link);
        //send an email

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("jsuitraining@gmail.com");
        message.setTo(toEmail);
        message.setSubject("mail for confirmation regarding your registration for learning academy");
        message.setText(message1+link);



        this.mailSender.send(message);

        return "mail sent successfully";
    }

    public String confirmRegistration(Integer myid) {

        Student studentdata = this.studentRepository.findById(myid).get();

        studentdata.setRegistrationConfirmation(true);

        this.studentRepository.save(studentdata);


 return "thank you for your confirmation, now you are a confirmed student";
    }
}

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(25);
//
//        mailSender.setUsername("jsuitraining@gmail.com");
//        mailSender.setPassword("pcqi qwqb uqem iblx");
//
//
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", "smtp");
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enabled", "true");
//
//        mailSender.setJavaMailProperties(properties);
//
//        return mailSender;
//
//    }
//    }
