package tech.guyi.component.sql.plus.sql.enums;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author guyi
 */
@Getter
@AllArgsConstructor
public enum SqlValueType {

    LONG("Long", e -> e instanceof SQLBigIntExpr ,value -> new SQLBigIntExpr((Long) value), e -> ((SQLBigIntExpr) e).getValue()),
    INTEGER("Integer", e -> e instanceof SQLIntegerExpr ,value -> new SQLIntegerExpr((Integer) value), e -> ((SQLIntegerExpr) e).getValue()),
    BOOLEAN("Boolean", e -> e instanceof SQLBooleanExpr ,value -> new SQLBooleanExpr((Boolean) value), e -> ((SQLBooleanExpr) e).getValue()),
    DOUBLE("Double", e -> e instanceof SQLDoubleExpr ,value -> new SQLDoubleExpr((Double) value), e -> ((SQLDoubleExpr) e).getValue()),
    DATE("Date", e -> e instanceof SQLDateExpr ,value -> new SQLDateExpr((String) value), e -> ((SQLDateExpr) e).getValue()),
    DATE_TIME("DateTime", e -> e instanceof SQLDateTimeExpr ,value -> new SQLDateTimeExpr((String) value), e -> ((SQLDateTimeExpr) e).getValue()),
    FLOAT("Float", e -> e instanceof SQLFloatExpr ,value -> new SQLFloatExpr((Float) value), e -> ((SQLFloatExpr) e).getValue()),
    STRING("String", e -> e instanceof SQLCharExpr ,value -> new SQLCharExpr(value.toString()), e -> ((SQLCharExpr) e).getValue());

    private final String name;
    private final Function<SQLExpr, Boolean> filter;
    private final Function<Object, SQLExpr> creator;
    private final Function<SQLExpr, Object> valueGetter;

    public static SqlValueType findByName(String name) {
        return Arrays.stream(values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public static Object getValue(SQLExpr expr) {
        return Arrays.stream(values())
                .filter(type -> type.filter.apply(expr))
                .findFirst()
                .map(type -> type.valueGetter.apply(expr))
                .orElse(expr);
    }

}
