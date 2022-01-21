package net.ltf.mp.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 删除操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDelete {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestDeleteById(){

        /*
        DELETE FROM tb_user WHERE id=?
        UPDATE tb_user SET deleted=1 WHERE id=? AND deleted=0
         */
        int i = userMapper.deleteById(13l);

        System.out.println(i);   //1

    }

    /**
     * 多条件删除
     */
    @Test
    public void TestDeleteByMap(){

        // map中的元素就是多个条件
        Map<String,Object> map = new HashMap<>();

        map.put("user_name","sunqi");
        map.put("age",33);

        // map元素之间AND连接
        /*
        DELETE FROM tb_user WHERE user_name = ? AND age = ?
         */
        int i = userMapper.deleteByMap(map);

        System.out.println("result => "+ i);  //result => 1

    }

    @Test
    public void TestDelete(){

        /*
        方法一：
         */
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*
        DELETE FROM tb_user WHERE (user_name = ? AND password = ?)
         */
//        queryWrapper.eq("user_name","zhangsan")
//                .eq("password","123456");
//        int delete = userMapper.delete(queryWrapper);
//        System.out.println("result => "+delete);  //result => 1

        /*
        方法二（推荐使用）：
         */
        User user = new User();
        user.setName("关羽");
        user.setAge(34);

        /*
        包装条件删除
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(user);
        /*
        DELETE FROM tb_user WHERE name=? AND age=?
         */
        int delete = userMapper.delete(queryWrapper);
        System.out.println("result => "+delete);  //result => 1

    }

    /*
    批量删除
     */
    @Test
    public void TestDeleteBatchIds(){

        /*
        批量删除，集合
         DELETE FROM tb_user WHERE id IN ( ? , ? )
         in()操作
         */
        int i = userMapper.deleteBatchIds(Arrays.asList(6l, 7l));
        System.out.println("result => "+i);   //result => 1
    }

}
