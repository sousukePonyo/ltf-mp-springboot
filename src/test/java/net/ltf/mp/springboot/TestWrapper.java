package net.ltf.mp.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWrapper {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testAllEq(){

        Map<String,Object> map = new HashMap<>();
        map.put("name","赵云");
        map.put("age",11);
        map.put("email",null);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ? AND age = ? AND email IS NULL)
        // null会被作为查询条件
//        wrapper.allEq(map);
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ? AND age = ?)
        // null不会作为查询条件
//        wrapper.allEq(map,false);
        /*
        过滤器，k代表map的key,v代表map的value
        */
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ? AND age = ?)
//        wrapper.allEq((k, v) -> (k.equals("name") || k.equals("age")), map);
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ?)
        wrapper.allEq((k, v) -> (k.equals("name") || k.equals("id")), map);
        final List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testEqAndInAndGe(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", "123456")
                .ge("age",21)
                .in("name", "张三","赵云","黄忠","刘备");
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (password = ? AND age >= ? AND name IN (?,?,?,?))
        final List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testLike(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //  SELECT id,user_name,name,age,email FROM tb_user WHERE (name LIKE %云%(String))
//        wrapper.like("name", "云");
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name NOT LIKE %云%(String))
//        wrapper.notLike("name", "云");
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name LIKE %云(String))
//        wrapper.likeLeft("name", "云");
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name LIKE 赵%(String))
        wrapper.likeRight("name", "赵");
        final List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testOrderBy(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("age");

        // SELECT id,user_name,name,age,email FROM tb_user ORDER BY age DESC
        final List<User> users = userMapper.selectList(wrapper);

        for (User user : users) {

            System.out.println(user);
        }
    }

    @Test
    public void testLogic(){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ? AND age = ?)
//        wrapper.eq("name", "赵云").eq("age", 11);
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (name = ? OR age = ?)
        wrapper.eq("name", "赵云").or().eq("age", 34);

        final List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {

            System.out.println(user);
        }

    }

}
