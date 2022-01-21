package net.ltf.mp.springboot.plugins;

import lombok.Singular;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

@Intercepts({@Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class}
)})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 拦截方法，具体业务逻辑的实现
        System.out.println("执行了update方法");
        return invocation.proceed();   // 放行
    }

    @Override
    public Object plugin(Object target) {

        // 创建target（目标）对象的代理对象，目的是将该拦截器加入到该对象
        // 执行四次，
        /*
        1. Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
        2. ParameterHandler (getParameterObject, setParameters)
        3. ResultSetHandler (handleResultSets, handleOutputParameters)
        4. StatementHandler (prepare, parameterize, batch, update, query)
         */
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // 属性设置
    }
}
