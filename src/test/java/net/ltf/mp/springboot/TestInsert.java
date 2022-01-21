package net.ltf.mp.springboot;

import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.enums.GenderEnum;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// SpringRunner extends SpringJUnit4ClassRunner,可以用SpringRunner代替
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestInsert {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestInsert(){

        User user = new User();

//        user.setName("孙策");
//        user.setUserName("sunce");
//        user.setAge(42);
//        user.setEmail("sunce@qq.com");
//        user.setPassword("123456");

        user.setName("大乔");
        user.setUserName("daqiao");
        user.setAge(33);
        user.setEmail("daqiao@qq.com");
        user.setPassword("123456");
        user.setGender(GenderEnum.WOMAN);


        /*
        实体类id未设置主键生成策略
        INSERT INTO tb_user ( id, user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ?, ? )
        将自动生成一个与id类型相同的主键
         */
//        int insert = userMapper.insert(user); // 返回受影响的行数
//        System.out.println(user.getId());   //Long类型的数
        /*
        设置主键生成策略为AUTO自动增长，不会插入id
        INSERT INTO tb_user ( user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ? )
         */
        int insert = userMapper.insert(user); // 返回受影响的行数

        System.out.println(user.getId()); //6

    }
}
