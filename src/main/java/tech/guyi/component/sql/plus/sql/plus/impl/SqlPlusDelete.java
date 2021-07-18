package tech.guyi.component.sql.plus.sql.plus.impl;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.guyi.component.sql.plus.sql.plus.SqlPlus;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

/**
 * @author guyi
 */
@Getter
@AllArgsConstructor
public class SqlPlusDelete implements SqlPlus {

    private final DbType dbType;
    private final SQLDeleteStatement statement;
    private final EntityNameSupplier nameSupplier;

    @Override
    public SQLExpr getWhere() {
        return this.statement.getWhere();
    }

    @Override
    public void setWhere(SQLExpr where) {
        this.statement.setWhere(where);
    }

    @Override
    public SQLTableSource getTable() {
        return this.statement.getTableSource();
    }

}
