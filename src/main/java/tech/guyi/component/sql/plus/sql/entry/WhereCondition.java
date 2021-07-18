package tech.guyi.component.sql.plus.sql.entry;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import lombok.AllArgsConstructor;
import lombok.Data;
import tech.guyi.component.sql.plus.sql.enums.SqlValueType;

import java.util.Optional;

/**
 * @author guyi
 */
@Data
@AllArgsConstructor
public class WhereCondition {

    private String name;
    private Object value;
    private SqlValueType type;
    private SQLBinaryOperator operator;

    public SQLExpr getNameExpr(String alias, String name) {
        String tmpName = Optional.ofNullable(name).orElse(this.name);
        return new SQLIdentifierExpr(
                Optional.ofNullable(alias)
                        .map(as -> String.format("%s.%s", alias, tmpName))
                        .orElse(tmpName));
    }

    public SQLExpr getValueExpr() {
        return this.type.getCreator().apply(this.value);
    }

}
