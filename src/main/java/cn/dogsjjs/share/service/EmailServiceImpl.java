package cn.dogsjjs.share.service;

import cn.dogsjjs.share.entity.Email;
import cn.dogsjjs.share.util.ResultEntity;
import cn.dogsjjs.share.util.SaltUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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

    @Override
    public ResultEntity<String> commonEmail(String email) {
        // 生成验证码
        String code = SaltUtils.getCode();
        // 将验证码保存进redis中
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(email,code);
        redisTemplate.expire(email,60*60, TimeUnit.SECONDS);// 有效时间为一个小时

        Email sendEmail = new Email();
        sendEmail.setTos(new String[]{email});
        sendEmail.setSubject("ShareLink重置密码");
        sendEmail.setContent("ShareLink正在重置您的密码，验证码是"+ code);
        return commonEmail(sendEmail);
    }

}
