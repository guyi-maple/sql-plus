package tech.guyi.component.sql.plus.executor;

import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusDelete;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusInsert;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusSelect;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusUpdate;

/**
 * 表权限提供器
 * @author guyi
 */
public interface SqlPlusExecutor {

    default void execute(SqlPlusSelect select){}

    default void execute(SqlPlusUpdate update){}

    default void execute(SqlPlusDelete delete){}

    default void execute(SqlPlusInsert insert){}

}
