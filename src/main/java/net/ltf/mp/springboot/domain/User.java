package net.ltf.mp.springboot.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.ltf.mp.springboot.enums.GenderEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName("tb_user")   在application.properties中配置了表前缀
// 继承model开启ActiveRecord
public class User extends Model<User> {

    // 设置主键生成策略，其他的还有NONE、INPUT、ASSIGN_ID、ASSIGN_UUID
//    @TableId(type = IdType.AUTO)  在application.properties中配置了主键生成策略
    private Long id;

    private String userName;

    /*
    指定此字段不返回
    @JsonIgnore类似
    fill自动填充字段，FieldFill.INSERT插入时自动填充
     */
    @TableField(select = false,fill = FieldFill.INSERT)
    private String password;

    private String name;

    private Integer age;

    @TableField(value = "email")  // 指定数据库中的字段名，将会用这个名字去数据库查询
    private String email;

    /*
    表示这个字段在数据库中不存在，
    当这个值不为空时，将不映射这个属性，
    不设置时不为空时会报错。
     */
    @TableField(exist = false)
    private String address;

    @TableField(fill = FieldFill.INSERT)
    @Version   // 版本号控制
    private Integer version;

    @TableLogic  // 逻辑删除，状态标志
    private Integer deleted;
    // 枚举
    private GenderEnum gender;
}
