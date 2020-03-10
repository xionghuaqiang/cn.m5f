package org.xhq.springbootgetmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@RestController
public class HelloController {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;
    //待发送的邮件
    String[] email ={"xx@qq.com"};

    @RequestMapping("hello")
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("基于SpringBoot实现邮件发送");
//        你的邮件
        helper.setFrom("xx@qq.comm");
        helper.setTo(email);
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        Context context = new Context();
        context.setVariable("username", "xx");
        context.setVariable("num","22");
        context.setVariable("salary", "99999");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }


}
