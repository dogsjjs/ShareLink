package cn.dogsjjs.share.service.impl;

import cn.dogsjjs.share.entity.User;
import cn.dogsjjs.share.mapper.UserMapper;
import cn.dogsjjs.share.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-10-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return getOne(queryWrapper);
    }

    @Override
    public boolean checkCode(String email, String code) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String redisCode = operations.get(email);
        return Objects.equals(redisCode,code);
    }

    @Override
    public User findUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        return getOne(queryWrapper);
    }

    @Override
    public boolean resetUser(User user) {
        String password = user.getPassword();
        String salt = user.getSalt();
        Md5Hash md5Hash = new Md5Hash(password, salt,1024);
        user.setPassword(md5Hash.toHex());
        return updateById(user);
    }

}
