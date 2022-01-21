package net.ltf.mp.springboot;


import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 通过id更新
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUpdateById {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestUpdate(){

        User user = new User();

        user.setId(3l);
        user.setName("黄忠");
        user.setUserName("huangzhong");
        user.setPassword("12345");

        /*
        UPDATE tb_user SET user_name=?, password=?, name=? WHERE id=?
         */
        int result = userMapper.updateById(user);

        System.out.println("result => "+result); //result => 1

    }

}
