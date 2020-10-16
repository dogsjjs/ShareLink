package cn.dogsjjs.share.shiro;

import cn.dogsjjs.share.entity.User;
import cn.dogsjjs.share.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

// 自定义Realm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("授权");
        //SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //
        //authorizationInfo.addStringPermission("user:add");
        return null;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取当前用户名
        String principal = (String) authenticationToken.getPrincipal();
        // 连接数据库查询用户
        User user = userService.findUserByUsername(principal);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        String password = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, password, ByteSource.Util.bytes(salt), this.getName());
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        user.setPassword(null);
        session.setAttribute("loginUser",user);
        return authenticationInfo;
    }

}
