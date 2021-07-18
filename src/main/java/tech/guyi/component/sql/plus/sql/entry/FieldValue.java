package tech.guyi.component.sql.plus.sql.entry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author guyi
 */
@Getter
@ToString
@AllArgsConstructor
public class FieldValue {

    private final String name;
    private final Object value;

}
