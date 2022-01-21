package net.ltf.mp.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.ltf.mp.springboot.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAR {

    @Test
    public void testSelectById(){
        User user = new User();
        user.setId(9l);
        // SELECT id,user_name,name,age,email FROM tb_user WHERE id=?
        User result = user.selectById(); // 底层也是通过UserMapper来查询数据库的
        System.out.println(result);
    }

    @Test
    public void testSelectWrapper(){
        User user = new User();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age", 20);  // 年龄大于等于20
        // SELECT id,user_name,name,age,email FROM tb_user WHERE (age >= ?)
        final List<User> userList = user.selectList(wrapper);
        for (User user1 : userList) {
            System.out.println(user1);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("马超");
        user.setAge(32);
        user.setPassword("123456");
        user.setUserName("machao");
        user.setEmail("machao@qq.com");

        // INSERT INTO tb_user ( user_name, password, name, age, email ) VALUES ( ?, ?, ?, ?, ? )
        final boolean insert = user.insert();
        System.out.println("result => "+insert); //true
    }

    @Test
    public void testUpdate(){

        User user = new User();
        user.setId(12l);    // 查询条件
        user.setAge(29);
        // 设置版本号,乐观锁插件
        user.setVersion(user.selectById().getVersion());

        // UPDATE tb_user SET age=? WHERE id=?
        // UPDATE tb_user SET age=?, version=? WHERE id=? AND version=?
        final boolean update = user.updateById();
        System.out.println("result => "+update);

    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setId(3l);

        // DELETE FROM tb_user WHERE id=?
        boolean delete = user.deleteById();
        System.out.println("result => "+delete); // result => true
    }

    /*
    测试防全表更新与删除操作插件
    BlockAttackInnerInterceptor
     */
    @Test
    public void testUpdateAll(){

        User user = new User();
        user.setAge(29);

        // UPDATE tb_user SET age=?

        //Prohibition of table update operation 禁止全表操作
        final boolean update = user.update(null);  // 执行全表更新操作
        System.out.println("result => "+update);

    }
}
