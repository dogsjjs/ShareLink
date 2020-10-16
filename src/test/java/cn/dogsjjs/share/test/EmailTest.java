package cn.dogsjjs.share.test;

import cn.dogsjjs.share.entity.Email;
import cn.dogsjjs.share.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmail(){
        Email email = new Email();
        email.setTos(new String[]{"2138183713@qq.com"});
        email.setSubject("又是想你的一天");
        email.setContent("一点点普普通通的内容");
        emailService.commonEmail(email);
    }

}
