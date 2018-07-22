package it.discovery.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class InitBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1 && params[0] == ApplicationContext.class) {
                    try {
                        method.invoke(bean, applicationContext);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return bean;
    }
}
