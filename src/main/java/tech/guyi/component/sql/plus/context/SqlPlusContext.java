package tech.guyi.component.sql.plus.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author guyi
 */
public class SqlPlusContext {

    private static final ThreadLocal<Map<Integer, Object>> updateLocal = new ThreadLocal<>();

    public static void setUpdateParameter(int index, Object value) {
        Map<Integer, Object> map = Optional.ofNullable(updateLocal.get())
                .orElseGet(HashMap::new);
        map.put(index, value);
        updateLocal.set(map);
    }
    public static Optional<Object> getUpdateParameter(int index) {
        return Optional.ofNullable(updateLocal.get())
                .map(map -> map.get(index));
    }

}
