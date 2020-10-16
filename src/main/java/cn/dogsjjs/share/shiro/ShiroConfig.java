package cn.dogsjjs.share.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 创建realm对象
    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        // 修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法
        credentialsMatcher.setHashAlgorithmName("md5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }

    // DefaultWebSecurityManager 安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // ShiroFilterFactoryBean 过滤器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro的内置过滤器
        Map<String, String> map = new LinkedHashMap<>();

        map.put("/","authc");
        map.put("/index","authc");

        map.put("/user/toLogin","anon");// 前往登录页面无需认证
        map.put("/user/toRegister","anon");// 前往注册页面无需认证
        map.put("/user/login","anon");// 登录请求
        map.put("/user/register","anon");// 注册请求

        factoryBean.setFilterChainDefinitionMap(map);

        // 设置登录页面地址
        factoryBean.setLoginUrl("/user/toLogin");
        return factoryBean;
    }

    // 整合ShiroDialect
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

}
