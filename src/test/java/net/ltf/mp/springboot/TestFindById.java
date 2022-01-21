package net.ltf.mp.springboot;


import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFindById {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestFindById(){

        /*
        SELECT id,user_name,name,age,email FROM tb_user WHERE id=?
         */
        User user = userMapper.selectById(3);

        /*
        password指定了不返回
        User(id=3, userName=wangwu, password=null, name=王五, age=28, email=test3@itcast.cn, address=null)
         */
        System.out.println(user);
    }
}
