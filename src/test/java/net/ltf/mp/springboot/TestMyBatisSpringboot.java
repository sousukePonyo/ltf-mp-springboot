package net.ltf.mp.springboot;

import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMyBatisSpringboot {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void selectAllList(){

        /*
        SELECT id,user_name,password,name,age,email FROM tb_user
         */
        final List<User> userList = userMapper.selectList(null);

        for (User user : userList) {
            System.out.println(user);
        }

    }

}
