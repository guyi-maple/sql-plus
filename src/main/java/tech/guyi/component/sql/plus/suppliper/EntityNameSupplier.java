package tech.guyi.component.sql.plus.suppliper;

import java.util.Optional;

/**
 * @author guyi
 */
public interface EntityNameSupplier {

    default Optional<String> get(Class<?> classes) {
        return Optional.of(classes.getSimpleName());
    }

    Optional<String> getEntity(String table);

    Optional<String> getTable(Class<?> classes);

    Optional<String> getField(String table, String origin);

    Optional<String> getField(Class<?> classes, String origin);

}
