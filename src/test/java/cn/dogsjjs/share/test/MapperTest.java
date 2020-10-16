package cn.dogsjjs.share.test;

import cn.dogsjjs.share.entity.User;
import cn.dogsjjs.share.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveTest(){
        User user = new User();
        user.setUsername("断水流");
        user.setPassword("123456");
        user.setAvatar(null);
        user.setEmail("1428204486@qq.com");
        user.setType(1);
        boolean save = userService.save(user);
        System.out.println(save);
    }

    @Test
    public void deleteTest(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","大师兄");
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
        userService.removeById(user.getId());
    }

    @Test
    public void updateTest(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","大师兄");
        User user = userService.getOne(queryWrapper);
        user.setPassword("45787878");
        boolean update = userService.updateById(user);
        System.out.println(update);
    }

}
