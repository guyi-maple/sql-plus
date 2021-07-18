package tech.guyi.component.sql.plus.sql.entry;

import tech.guyi.component.sql.plus.sql.plus.SqlPlus;

import java.util.LinkedList;
import java.util.List;

/**
 * @author guyi
 */
public class WhereBuilder {

    private final SqlPlus plus;
    private final List<FieldCondition> conditions;

    public WhereBuilder(SqlPlus plus) {
        this.plus = plus;
        this.conditions = new LinkedList<>();
    }

    public WhereBuilder condition(FieldCondition condition) {
        this.conditions.add(condition);
        return this;
    }

    public WhereBuilder condition(String name, Object value, String operator) {
        return this.condition(new FieldCondition(name, value, operator));
    }

    public void end() {
        this.plus.where(this.conditions);
    }
}
