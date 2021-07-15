package com.guyi.component.test;

import com.guyi.component.test.db.User;
import org.springframework.stereotype.Component;
import tech.guyi.component.sql.plus.executor.ClassSqlPlusExecutor;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusSelect;

/**
 * @author guyi
 */
@Component
public class TestSqlPlusExecutor implements ClassSqlPlusExecutor {

    @Override
    public void execute(SqlPlusSelect select) {
        select.where()
                .condition("createTime", 200,">=", Integer.class)
                .end();
    }

    @Override
    public Class<?> forClass() {
        return User.class;
    }
}
