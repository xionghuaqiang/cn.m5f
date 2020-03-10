package org.xhq.springbootgetmail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class SpringbootgetmailApplicationTests {
    @Autowired
    JavaMailSender javaMailSender;
    @Test
    void contextLoads() {
    }

}
