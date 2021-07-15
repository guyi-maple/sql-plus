package tech.guyi.component.sql.plus;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.guyi.component.sql.plus.datasource.DataSourceBeanPostProcessor;
import tech.guyi.component.sql.plus.suppliper.defaults.HibernateFieldNameSupplier;

/**
 * @author guyi
 */
@Configuration
public class DataPermissionAutoConfiguration {

    @Bean
    public DataSourceBeanPostProcessor dataSourceBeanPostProcessor() {
        return new DataSourceBeanPostProcessor();
    }

    @Bean
    @ConditionalOnClass(SessionFactory.class)
    public HibernateFieldNameSupplier hibernateFieldNameSupplier() {
        return new HibernateFieldNameSupplier();
    }

}
