package net.ltf.mp.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.ltf.mp.springboot.domain.User;
import net.ltf.mp.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询操作
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSelect {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestSelectById(){

        /*
        通过id查询
        SELECT id,user_name,name,age,email FROM tb_user WHERE id=?
         */
        User user = userMapper.selectById(16l);

        System.out.println(user);
        //User(id=8, userName=liubei, password=null, name=刘备, age=36, email=liubei@qq.com, address=null)

    }

    /*
    批量查找
     */
    @Test
    public void TestSelectBatchIds(){

        /*
        SELECT id,user_name,name,age,email FROM tb_user WHERE id IN ( ? , ? , ? , ? )
        in()操作
         */
        List<User> users = userMapper.selectBatchIds(Arrays.asList(3l, 4l, 8l, 146l));

        /*
        User(id=3, userName=huangzhong, password=null, name=黄忠, age=28, email=test3@itcast.cn, address=null)
        User(id=4, userName=zhaoyun, password=null, name=赵云, age=11, email=zhaoyun@qq.com, address=null)
        User(id=8, userName=liubei, password=null, name=刘备, age=36, email=liubei@qq.com, address=null)
         */
        for (User user : users) {
            System.out.println(user);
        }

    }

    /*
    查询一条数据
     */
    @Test
    public void TestSelectOne(){

        /*
        设置查询条件
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name","zhaoyun");

        /*
        当查询到多条数据时，会抛出异常:One record is expected, but the query result is multiple records
        SELECT id,user_name,name,age,email FROM tb_user WHERE (user_name = ?)
         */
        User user = userMapper.selectOne(queryWrapper);

        System.out.println(user);
        //User(id=4, userName=zhaoyun, password=null, name=赵云, age=11, email=zhaoyun@qq.com, address=null)

    }

    /*
    查询记录总条数
     */
    @Test
    public void TestSelectCount(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*
        表示年龄大于20的数据
         */
        queryWrapper.gt("age",20);

        /*
        SELECT COUNT( * ) FROM tb_user WHERE (age > ?)
         */
        Long count = userMapper.selectCount(queryWrapper);
        System.out.println(count);  //2

    }

    /*
    查询全部数据
     */
    @Test
    public void TestSelectList(){

        QueryWrapper<User> queryWrapper = new QueryWrapper();
        /*
        模糊查询，查找email包含@qq的数据
         */
        queryWrapper.like("email","@qq");

        /*
        SELECT id,user_name,name,age,email FROM tb_user WHERE (email LIKE ?)
        Parameters: %@qq%(String)
         */
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
            /*
            User(id=4, userName=zhaoyun, password=null, name=赵云, age=11, email=zhaoyun@qq.com, address=null)
            User(id=8, userName=liubei, password=null, name=刘备, age=36, email=liubei@qq.com, address=null)
             */
        }

    }

    @Test
    public void TestSelectPage(){

        /*
        current:当前页
        size:每页最大数
        public Page(long current, long size) {
            this(current, size, 0);
        }
         */
        Page<User> page = new Page(2,1);  // 第一页，一条数据

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20);
        /*
        <P extends IPage<T>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        SELECT COUNT(*) AS total FROM tb_user WHERE (age > ?)    Parameters:20(Integer)
        SELECT id,user_name,name,age,email FROM tb_user WHERE (age > ?) LIMIT ? OFFSET ? (0开始可省略起始索引)
        Parameters: 20(Integer), 1(Long), 1(Long)
         */
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);

        System.out.println("总条数："+userPage.getTotal());
        System.out.println("当前页："+userPage.getCurrent());
        System.out.println("每页最大记录数："+userPage.getSize());
        System.out.println("总页数："+userPage.getPages());
        // 所有记录
        final List<User> records = userPage.getRecords();
        for (User record : records) {
            System.out.println(record);
            /*
            User(id=4, userName=zhaoyun, password=null, name=赵云, age=11, email=zhaoyun@qq.com, address=null)
            User(id=8, userName=liubei, password=null, name=刘备, age=36, email=liubei@qq.com, address=null)
             */
        }

    }

    @Test
    public void findById(){
        /*
        select * from tb_user where id = ?
         */
        final User user = userMapper.findById(3l);

        /*
        User(id=3, userName=huangzhong, password=12345, name=黄忠, age=28, email=test3@itcast.cn, address=null)
         */
        System.out.println(user);
    }

    /*
    指定字段返回
     */
    @Test
    public void TestSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // SELECT id,name,age FROM tb_user
        wrapper.select("id,name,age");

        final List<User> users = userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
