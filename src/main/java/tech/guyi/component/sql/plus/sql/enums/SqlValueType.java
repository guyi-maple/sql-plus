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

    LONG("Long", value -> new SQLBigIntExpr((Long) value)),
    INTEGER("Integer", value -> new SQLIntegerExpr((Integer) value)),
    BOOLEAN("Boolean", value -> new SQLBooleanExpr((Boolean) value)),
    DOUBLE("Double", value -> new SQLDoubleExpr((Double) value)),
    DATE("Date", value -> new SQLDateExpr((String) value)),
    DATE_TIME("DateTime", value -> new SQLDateTimeExpr((String) value)),
    FLOAT("Float", value -> new SQLFloatExpr((Float) value)),
    STRING("String", value -> new SQLCharExpr(value.toString()));

    private final String name;
    private final Function<Object, SQLExpr> creator;

    public static SqlValueType findByName(String name) {
        return Arrays.stream(values())
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
