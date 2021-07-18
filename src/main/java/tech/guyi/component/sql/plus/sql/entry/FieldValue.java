package tech.guyi.component.sql.plus.sql.entry;

import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.statement.SQLUpdateSetItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import tech.guyi.component.sql.plus.sql.enums.SqlValueType;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

/**
 * @author guyi
 */
@Getter
@ToString
@AllArgsConstructor
public class FieldValue {

    private final String name;
    private final Object value;

    public SQLUpdateSetItem toUpdateItem(String table, EntityNameSupplier supplier) {
        SQLUpdateSetItem item = new SQLUpdateSetItem();
        item.setColumn(new SQLIdentifierExpr(supplier.getField(table, this.name).orElse(this.name)));
        item.setValue(SqlValueType.findByName(this.value.getClass().getSimpleName()).getCreator().apply(this.value));
        return item;
    }

}
