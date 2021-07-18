package tech.guyi.component.sql.plus.sql.plus.impl;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tech.guyi.component.sql.plus.context.SqlPlusContext;
import tech.guyi.component.sql.plus.sql.entry.FieldValue;
import tech.guyi.component.sql.plus.sql.plus.SqlPlus;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guyi
 */
@Getter
@AllArgsConstructor
public class SqlPlusUpdate implements SqlPlus {

    private final DbType dbType;
    private final SQLUpdateStatement statement;
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

    public void update(List<FieldValue> values) {
        String table = this.getTableName();

        Map<String, Integer> origins = new HashMap<>();
        for (int i = 0; i < this.statement.getItems().size(); i++) {
            SQLUpdateSetItem item = this.statement.getItems().get(i);
            origins.put(item.getColumn().toString(), i + 1);
        }

        values.forEach(value -> {
            if (origins.containsKey(value.getName())) {
                SqlPlusContext.setUpdateParameter(origins.get(value.getName()), value.getValue());
            } else {
                this.statement.addItem(value.toUpdateItem(table, this.nameSupplier));
            }
        });
    }

}
