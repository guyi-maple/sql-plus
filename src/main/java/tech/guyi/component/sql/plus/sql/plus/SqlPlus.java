package tech.guyi.component.sql.plus.sql.plus;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import tech.guyi.component.sql.plus.sql.entry.FieldCondition;
import tech.guyi.component.sql.plus.sql.entry.WhereBuilder;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * SQL权限接口
 * @author guyi
 */
public interface SqlPlus {

    EntityNameSupplier getNameSupplier();

    DbType getDbType();

    SQLExpr getWhere();

    void setWhere(SQLExpr where);

    SQLTableSource getTable();

    default String getTableName() {
        return ((SQLExprTableSource) this.getTable()).getExpr().toString();
    }

    SQLStatement getStatement();

    default String toSql() {
        return this.getStatement().toString();
    }

    default WhereBuilder where() {
        return new WhereBuilder(this);
    }

    default void where(List<FieldCondition> entries) {
        String table = this.getTableName();
        List<SQLBinaryOpExpr> es = entries.stream()
                .map(FieldCondition::to)
                .map(field -> {
                    SQLBinaryOpExpr expr = new SQLBinaryOpExpr();
                    expr.setDbType(this.getDbType());
                    expr.setRight(field.getValueExpr());
                    expr.setOperator(field.getOperator());
                    expr.setLeft(field.getNameExpr(this.getTable().getAlias(), this.getNameSupplier().getField(table, field.getName()).orElse(field.getName())));
                    return expr;
                })
                .collect(Collectors.toList());

        if (es.isEmpty()) {
            return;
        }

        SQLExpr where;
        if (es.size() == 1) {
            where = es.get(0);
        } else {
            SQLBinaryOpExpr parent = new SQLBinaryOpExpr();
            parent.setDbType(this.getDbType());
            parent.setLeft(es.get(0));
            parent.setOperator(SQLBinaryOperator.BooleanAnd);
            for (int i = 1; i < es.size(); i++) {
                if (i == es.size() - 1) {
                    parent.setRight(es.get(i));
                } else {
                    SQLBinaryOpExpr tmp = new SQLBinaryOpExpr();
                    tmp.setDbType(this.getDbType());
                    tmp.setLeft(es.get(i));
                    tmp.setOperator(SQLBinaryOperator.BooleanAnd);
                    parent.setRight(tmp);
                    parent = tmp;
                }
            }
            where = parent;
        }

        this.setWhere(
                Optional.ofNullable(this.getWhere())
                        .map(origin -> {
                            SQLBinaryOpExpr expr = new SQLBinaryOpExpr();
                            expr.setLeft(origin);
                            expr.setRight(where);
                            expr.setDbType(this.getDbType());
                            expr.setOperator(SQLBinaryOperator.BooleanAnd);
                            return expr;
                        })
                        .map(e -> (SQLExpr) e)
                        .orElse(where)
        );
    }

}
