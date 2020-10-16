package cn.dogsjjs.share.controller;


import cn.dogsjjs.share.entity.User;
import cn.dogsjjs.share.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-10-16
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        RedirectAttributes attributes){
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);// 执行登录方法
            return "redirect:/";
        } catch (UnknownAccountException e){// 用户名不存在
            e.printStackTrace();
            attributes.addFlashAttribute("msg","用户名错误！");
            return "redirect:/user/toLogin";
        } catch (IncorrectCredentialsException e){// 密码错误
            e.printStackTrace();
            attributes.addFlashAttribute("msg","密码错误！");
            attributes.addFlashAttribute("username",username);
            return "redirect:/user/toLogin";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("msg","登录失败！");
            return "redirect:/user/toLogin";
        }
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes attributes){
        User userByUsername = userService.findUserByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(userByUsername)){
            attributes.addFlashAttribute("msg","该用户名已被注册，请重新输入！");
            return "redirect:/user/toRegister";
        }
        boolean flag = userService.save(user);
        if (flag){
            return "redirect:/user/toLogin";
        }else {
            attributes.addFlashAttribute("msg","注册失败，请重试！");
            return "redirect:/user/toRegister";
        }
    }

}

