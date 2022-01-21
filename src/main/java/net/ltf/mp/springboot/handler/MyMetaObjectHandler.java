package net.ltf.mp.springboot.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时进行自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 根据字段名获取字段，metaObject为实体类
        Object password = getFieldValByName("password", metaObject);
        Object version = getFieldValByName("version", metaObject);
        if(null == password){
            // 设置默认的字段值
            setFieldValByName("password", "666666", metaObject);
        }
        if(null == version){
            // 设置默认的字段值
            setFieldValByName("version", 1, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
