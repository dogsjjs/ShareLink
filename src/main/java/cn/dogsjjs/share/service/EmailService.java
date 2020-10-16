package cn.dogsjjs.share.service;

import cn.dogsjjs.share.entity.Email;
import cn.dogsjjs.share.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public ResultEntity<String> commonEmail(Email email){
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(email.getTos());
        //邮件标题
        message.setSubject(email.getSubject());
        //邮件内容
        message.setText(email.getContent());
        try {
            mailSender.send(message);
            return ResultEntity.successWithData("普通邮件发送成功!");
        } catch (MailException e) {
            e.printStackTrace();
            return ResultEntity.failed("普通邮件发送失败!");
        }
    }

}
