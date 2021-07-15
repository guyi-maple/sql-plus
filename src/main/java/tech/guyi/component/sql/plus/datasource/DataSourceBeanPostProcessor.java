package tech.guyi.component.sql.plus.datasource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author guyi
 */
public class DataSourceBeanPostProcessor implements BeanPostProcessor {

    @Resource
    private ApplicationContext context;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            return new PlusDataSource((DataSource) bean, context);
        }
        return bean;
    }
}
