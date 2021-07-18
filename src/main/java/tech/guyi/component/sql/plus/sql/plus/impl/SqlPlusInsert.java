package tech.guyi.component.sql.plus.sql.plus.impl;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
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
public class SqlPlusInsert implements SqlPlus {

    private final DbType dbType;
    private final SQLInsertStatement statement;
    private final EntityNameSupplier nameSupplier;

    @Override
    public SQLExpr getWhere() {
        return null;
    }

    @Override
    public void setWhere(SQLExpr where) {
    }

    @Override
    public SQLTableSource getTable() {
        return this.statement.getTableSource();
    }

    public void insert(List<FieldValue> values) {
        String table = this.getTableName();

        Map<String, Integer> origins = new HashMap<>();
        for (int i = 0; i < this.statement.getColumns().size(); i++) {
            SQLExpr item = this.statement.getColumns().get(i);
            origins.put(this.nameSupplier.getField(table, item.toString()).orElse(item.toString()), i + 1);
        }

        values.forEach(value -> {
            if (origins.containsKey(value.getName())) {
                SqlPlusContext.setUpdateParameter(origins.get(value.getName()), value.getValue());
            } else {
                this.statement.getValuesList().forEach(clause -> clause.addValue(value.getValue()));
            }
        });
    }

}
