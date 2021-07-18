package tech.guyi.component.sql.plus.sql.entry;

import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import tech.guyi.component.sql.plus.sql.enums.SqlValueType;

import java.util.Arrays;

/**
 * @author guyi
 */
@Getter
@Builder
@ToString
@AllArgsConstructor
public class FieldCondition {

    private final String name;
    private final Object value;
    private final String operator;

    public WhereCondition to() {
        return new WhereCondition(
                name,
                value,
                SqlValueType.findByName(this.value.getClass().getSimpleName()),
                Arrays.stream(SQLBinaryOperator.values())
                        .filter(op -> op.getName().equals(this.operator))
                        .findFirst()
                        .orElse(null)
        );
    }

}
