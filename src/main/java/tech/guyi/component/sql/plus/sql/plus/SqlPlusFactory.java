package tech.guyi.component.sql.plus.sql.plus;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusDelete;
import tech.guyi.component.sql.plus.sql.plus.impl.SqlPlusInsert;
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

        if (statement instanceof SQLDeleteStatement) {
            return Optional.of(new SqlPlusDelete(type, (SQLDeleteStatement) statement, nameSupplier));
        }

        if (statement instanceof SQLInsertStatement) {
            return Optional.of(new SqlPlusInsert(type, (SQLInsertStatement) statement, nameSupplier));
        }
        return Optional.empty();
    }

}
