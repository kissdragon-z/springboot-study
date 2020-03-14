package com.example.springbootstudy.factory;

import com.example.springbootstudy.exception.BeanNotFoundException;
import com.example.springbootstudy.self.SelfBaseService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SelfBeanFactory implements ApplicationContextAware, InitializingBean {

    private static final String prefix = "com.example.springbootstudy.self.";

    private ApplicationContext applicationContext;

    private ConfigurableApplicationContext configurableApplicationContext;

    private DefaultListableBeanFactory defaultListableBeanFactory;

    private SelfClassLoader classLoader = new SelfClassLoader();

    private Map<String, Class> classMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
        this.configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        this.defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();

    }

    @Override
    public void afterPropertiesSet() throws Exception {

        init();

    }

    private void init() {

        try {

            classLoader.fetchSelfClasses();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

    }

    public void registry(String className) {

        String className1 = prefix + className;

        try {

            Class<?> selfClass = classLoader.loadClass(className1);

            classMap.put(className, selfClass);

//            Object newInstance = selfClass.newInstance();

            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(selfClass);

//            beanDefinitionBuilder.setAutowireMode();

            defaultListableBeanFactory.registerBeanDefinition(selfClass.getName(), beanDefinitionBuilder.getRawBeanDefinition());

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (Exception e1) {
            
            e1.printStackTrace();
        }

    }

    public void remove(String className) {

        if (classMap.containsKey(className)) {

            defaultListableBeanFactory.removeBeanDefinition(classMap.get(className).getName());

        } else {

        }

    }

    public SelfBaseService getBean(String className) throws BeanNotFoundException {

        if (classMap.containsKey(className)) {

            return (SelfBaseService) applicationContext.getBean(classMap.get(className));

        }

        new BeanNotFoundException(className + " has no such bean.");

        return null;
    }
}
