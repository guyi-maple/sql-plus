package com.guyi.component.test;

import com.guyi.component.test.db.User;
import com.guyi.component.test.db.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author guyi
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Resource
    private UserRepository repository;

//    @Test
    public void add() {
        User user = new User();
        user.setUsername("张三");
        user.setSex(1);
        user.setAge(10);
        user.setCreateTime(100);
        this.repository.save(user);

        user = new User();
        user.setUsername("李四");
        user.setSex(0);
        user.setAge(20);
        user.setCreateTime(200);
        this.repository.save(user);
    }

    @Test
    public void select() {
        System.out.println(this.repository.findAll());
    }

}
