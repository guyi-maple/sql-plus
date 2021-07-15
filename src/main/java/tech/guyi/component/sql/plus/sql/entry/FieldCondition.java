package tech.guyi.component.sql.plus.sql.entry;

import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.guyi.component.sql.plus.sql.enums.SqlValueType;

import java.util.Arrays;

/**
 * @author guyi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldCondition {

    private String name;
    private Object value;
    private String operator;
    private Class<?> classes;

    public WhereCondition to() {
        return new WhereCondition(
                name,
                value,
                SqlValueType.findByName(this.classes.getSimpleName()),
                Arrays.stream(SQLBinaryOperator.values())
                        .filter(op -> op.getName().equals(this.operator))
                        .findFirst()
                        .orElse(null)
        );
    }

}
