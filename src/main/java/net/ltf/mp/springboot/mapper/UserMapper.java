package net.ltf.mp.springboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.ltf.mp.springboot.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    /*
    通过id查找
     */
    User findById(Long id);

    List<User> findList();

}
