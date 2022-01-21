package net.ltf.mp.springboot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import net.ltf.mp.springboot.plugins.MyInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置类
 */
// 配置mapper扫描包
@MapperScan("net.ltf.mp.springboot.mapper")
@Configuration
public class MyBatisPlusConfig {

    /*
    分页插件
    分页拦截器，旧版已弃用
     */
//    @Bean
//    public PaginationInnerInterceptor paginationInnerInterceptor(){
//        return new PaginationInnerInterceptor();
//    }

    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 分析插件，防止全表的更新与删除
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }
    /*
    自定义拦截器
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }


    /*老版本已弃用
    执行分析插件
    阻断全表的更新或删除操作
    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor(){
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser());
        sqlExplainInterceptor.setSqlParserList(sqlParserList);
        }
     */

}
