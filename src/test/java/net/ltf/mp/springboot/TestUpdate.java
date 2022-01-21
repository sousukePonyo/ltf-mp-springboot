package net.ltf.mp.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 根据条件更新
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUpdate {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestQueryWrapper(){

        User user = new User();

        user.setName("孙琪");
        user.setAge(33);
        /*
        QueryWrapper可以设置条件
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name","sunqi"); // column:字段名  val:值

        /*
        UPDATE tb_user SET name=?, age=? WHERE (user_name = ?)
         */
        int update = userMapper.update(user, queryWrapper);

        System.out.println("update => "+update);  //update => 1
    }

    @Test
    public void TestUpdateWrapper(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name","赵云").set("age",11).set("email","zhaoyun@qq.com")  // 要更新的字段
                .eq("user_name","zhaoliu");    // 条件

        /*
        UPDATE tb_user SET name=?,age=?,email=? WHERE (user_name = ?)
         */
        int update = userMapper.update(null, updateWrapper);

        System.out.println("update =>" + update);  //update =>1

    }

}
