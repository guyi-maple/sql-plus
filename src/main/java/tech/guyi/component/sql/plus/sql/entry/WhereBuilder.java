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

    public <T> WhereBuilder condition(String name, T value, String operator, Class<T> classes) {
        return this.condition(new FieldCondition(name, value, operator, classes));
    }

    public void end() {
        this.plus.where(this.conditions);
    }
}
