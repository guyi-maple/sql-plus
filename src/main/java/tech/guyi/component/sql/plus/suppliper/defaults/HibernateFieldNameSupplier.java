package tech.guyi.component.sql.plus.suppliper.defaults;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.metamodel.internal.MetamodelImpl;
import org.hibernate.persister.entity.HibernateColumnsWrapper;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import tech.guyi.component.sql.plus.suppliper.EntityNameSupplier;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author guyi
 */
public class HibernateFieldNameSupplier implements EntityNameSupplier, InitializingBean {

    @Getter
    @AllArgsConstructor
    public static class HibernateEntity {
        private final String name;
        private final String column;
        private final Map<String, String> fields;
    }

    private final HibernateColumnsWrapper wrapper = new HibernateColumnsWrapper();

    private final Map<String, HibernateEntity> entityMap = new HashMap<>();
    private final Map<String, HibernateEntity> tableMap = new HashMap<>();

    @Resource
    private ApplicationContext context;

    @Override
    public void afterPropertiesSet() {
        EntityManager manager = context.getBean(EntityManager.class);
        EntityManagerFactory entityManagerFactory = manager.getEntityManagerFactory();
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        MetamodelImpl metamodel = (MetamodelImpl) sessionFactory.getMetamodel();
        metamodel.entityPersisters()
                .values()
                .stream()
                .map(e -> (SingleTableEntityPersister) e)
                .forEach(e -> {
                    Map<String, String> columns = this.wrapper.getColumns(e);
                    HibernateEntity field = new HibernateEntity(
                            e.getMappedClass().getSimpleName(),
                            e.getTableName(),
                            columns
                    );
                    entityMap.put(field.getName(), field);
                    tableMap.put(field.getColumn(), field);
                });
    }

    @Override
    public Optional<String> get(Class<?> classes) {
        return Optional.ofNullable(this.entityMap.get(classes.getSimpleName()))
                .map(HibernateEntity::getColumn);
    }

    @Override
    public Optional<String> getEntity(String table) {
        return Optional.ofNullable(this.tableMap.get(table))
                .map(HibernateEntity::getName);
    }

    @Override
    public Optional<String> getTable(Class<?> classes) {
        return Optional.of(classes.getSimpleName())
                .map(this.entityMap::get)
                .map(HibernateEntity::getColumn);
    }

    @Override
    public Optional<String> getField(String table, String origin) {
        return Optional.ofNullable(this.tableMap.get(table))
                .map(HibernateEntity::getFields)
                .map(fields -> fields.get(origin));
    }

    @Override
    public Optional<String> getField(Class<?> classes, String origin) {
        return Optional.ofNullable(this.entityMap.get(classes.getSimpleName()))
                .map(HibernateEntity::getFields)
                .map(fields -> fields.get(origin));
    }

}
