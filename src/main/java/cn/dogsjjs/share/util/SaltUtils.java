package cn.dogsjjs.share.util;

import java.util.UUID;

public class SaltUtils {

    /**
     * 获取随机盐
     * @return
     */
    public static String getSalt(){
        return UUID.randomUUID().toString().split("-")[4];
    }

    /**
     * 获取六位数验证码
     * @return
     */
    public static String getCode(){
        return UUID.randomUUID().toString().split("-")[0].substring(2);
    }

}
