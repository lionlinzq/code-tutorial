package pers.lionlinzq.excel.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class BeanRegisterUtils implements BeanDefinitionRegistryPostProcessor {

    private BeanDefinitionRegistry registry;

    /**
     * 手动注册Bean对象
     * @param beanName bean的名称
     * @param bean bean的对象
     */
    public void registerBean(String beanName, Object bean){

        //将Bean对象转换成BeanDefintion对象
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(bean.getClass(), new Supplier() {
            @Override
            public Object get() {
                return bean;
            }
        }).getBeanDefinition();

        //注册Bean到Spring容器
        registry.registerBeanDefinition(beanName, beanDefinition);
    }


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        this.registry = registry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
