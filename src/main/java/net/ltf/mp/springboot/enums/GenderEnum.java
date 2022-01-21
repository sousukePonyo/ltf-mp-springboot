package net.ltf.mp.springboot.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum GenderEnum implements IEnum<Integer> {
    MAN(1,"男"),
    WOMAN(2,"女");

    private Integer value;
    private String gender;

    /*
    构造方法
     */
    GenderEnum(Integer value,String gender){
        this.value = value;
        this.gender = gender;
    }

    /*
    获取值
     */
    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
