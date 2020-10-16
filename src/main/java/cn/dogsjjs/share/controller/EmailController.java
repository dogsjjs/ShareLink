package cn.dogsjjs.share.controller;

import cn.dogsjjs.share.entity.User;
import cn.dogsjjs.share.service.EmailService;
import cn.dogsjjs.share.service.UserService;
import cn.dogsjjs.share.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @PostMapping("/send/email")
    public ResultEntity<String> sendEmail(@RequestParam("email") String email){
        User userByEmail = userService.findUserByEmail(email);
        if (ObjectUtils.isEmpty(userByEmail)){
            return ResultEntity.failed("该邮箱尚未注册！请重新输入！");
        }
        return emailService.commonEmail(email);
    }

}
