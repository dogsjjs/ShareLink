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

}
