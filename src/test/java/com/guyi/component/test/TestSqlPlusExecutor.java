package com.guyi.component.test;

import com.guyi.component.test.db.User;
import org.springframework.stereotype.Component;
import tech.guyi.component.sql.plus.executor.ClassSqlPlusExecutor;
import tech.guyi.component.sql.plus.sql.entry.FieldValue;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusSelect;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusUpdate;

import java.util.Collections;
import java.util.UUID;

/**
 * @author guyi
 */
@Component
public class TestSqlPlusExecutor implements ClassSqlPlusExecutor {

    @Override
    public void execute(SqlPlusSelect select) {
//        select.where()
//                .condition("createTime", 200,">=", Integer.class)
//                .end();
    }

    @Override
    public void execute(SqlPlusUpdate update) {
        update.update(Collections.singletonList(new FieldValue("username", UUID.randomUUID().toString())));
    }

    @Override
    public Class<?> forClass() {
        return User.class;
    }
}
