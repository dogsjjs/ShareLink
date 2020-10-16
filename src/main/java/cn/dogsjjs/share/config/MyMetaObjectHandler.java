package cn.dogsjjs.share.config;

import cn.dogsjjs.share.util.AvatarUtils;
import cn.dogsjjs.share.util.SaltUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String avatar = null;
        // 获取用户名
        String username = (String) this.getFieldValByName("username", metaObject);
        // 获取密码
        String password = (String) this.getFieldValByName("password", metaObject);
        try {
            // 生成头像
            avatar = AvatarUtils.getAvatar(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 生成随机盐
        String salt = SaltUtils.getSalt();
        // 明文密码进行MD5 + salt + hash
        Md5Hash md5Hash = new Md5Hash(password, salt,1024);
        this.setFieldValByName("salt", salt,metaObject);// 设置随机盐
        this.setFieldValByName("password", md5Hash.toHex(),metaObject);// 设置密码

        // 插入数据时设置头像
        this.setFieldValByName("avatar", avatar,metaObject);
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }

}
