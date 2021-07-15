package tech.guyi.component.sql.plus.sql.plus;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusSelect;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusUpdate;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

import java.util.Optional;

/**
 * @author guyi
 */
public class SqlPlusFactory {

    public static Optional<SqlPlus> create(EntityNameSupplier nameSupplier, String sql, DbType type){
        SQLStatement statement = SQLUtils.parseStatements(sql, type).get(0);
        if (statement instanceof SQLSelectStatement) {
            return Optional.of(new SqlPlusSelect(type, (SQLSelectStatement) statement, nameSupplier));
        }

        if (statement instanceof SQLUpdateStatement) {
            return Optional.of(new SqlPlusUpdate(type, (SQLUpdateStatement) statement, nameSupplier));
        }
        return Optional.empty();
    }

}
