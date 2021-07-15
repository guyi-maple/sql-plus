package tech.guyi.component.sql.plus.sql.plus.impl;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.guyi.component.sql.plus.sql.plus.SqlPlus;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

import java.util.List;
import java.util.Set;

/**
 * @author guyi
 */
@Getter
@AllArgsConstructor
public class SqlPlusSelect implements SqlPlus {

    private final DbType dbType;
    private final SQLSelectStatement statement;
    private final EntityNameSupplier nameSupplier;

    @Override
    public SQLExpr getWhere() {
        return this.statement.getSelect().getQueryBlock().getWhere();
    }

    @Override
    public void setWhere(SQLExpr where) {
        this.statement.getSelect().getQueryBlock().setWhere(where);
    }

    @Override
    public SQLTableSource getTable() {
        return this.statement.getSelect().getQueryBlock().getFrom();
    }

    public void select(Set<String> fields) {
        List<SQLSelectItem> items = this.statement.getSelect().getQueryBlock().getSelectList();
        items.clear();
        fields.stream()
                .map(SQLIdentifierExpr::new)
                .map(SQLSelectItem::new)
                .forEach(items::add);
    }

}
