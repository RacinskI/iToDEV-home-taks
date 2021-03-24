package automation.lib.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Wrapper to always return a reference to the Spring Application Context from
 * within non-Spring enabled beans. Unlike Spring MVC's WebApplicationContextUtils
 * we do not need a reference to the Servlet context for this. All we need is
 * for this bean to be initialized during application startup.
 */
@Component
@Lazy(false)
public class SpringApplicationContext {
    private static ApplicationContext CONTEXT;

    public SpringApplicationContext(ApplicationContext context) {
        if (CONTEXT == null) {
            CONTEXT = context;
        }
    }

    public static Object getBean(String beanName) throws BeansException {
        return CONTEXT.getBean(beanName);
    }

    public static <T> T getBean(Class<T> beanClass) throws BeansException {
        return CONTEXT.getBean(beanClass);
    }

    public static <T> T getBean(String beanName, Class<T> beanClass) throws BeansException {
        return CONTEXT.getBean(beanName, beanClass);
    }

    public static String getProperty(String name) {
        return CONTEXT.getEnvironment().getProperty(name);
    }

    public static <T> T getProperty(String name, Class<T> type) {
        return CONTEXT.getEnvironment().getProperty(name, type);
    }
}
