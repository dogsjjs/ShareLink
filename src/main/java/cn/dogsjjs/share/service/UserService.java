package cn.dogsjjs.share.service;

import cn.dogsjjs.share.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-10-16
 */
public interface UserService extends IService<User> {

    User findUserByUsername(String username);

}
