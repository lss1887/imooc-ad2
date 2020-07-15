package com.imooc.ad.index;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
//索引bean 的统一管理
//ApplicationContextAware 初始化了哪些bean PriorityOrdered bean初始化的顺序
public class DataTable implements ApplicationContextAware, PriorityOrdered {

    private static ApplicationContext applicationContext;

    private static final Map<Class,Object> dataTableMap = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            DataTable.applicationContext = applicationContext;
    }
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    @SuppressWarnings("all")
    private static<T> T bean(String beanName){
        return (T) applicationContext.getBean(beanName);
    }

//    DataTable.of(CreativeUnitIndex.class) 获取的服务实现类
    @SuppressWarnings("all")
    public  static <T> T of (Class<T> clazz){
        T instance = (T)dataTableMap.get(clazz);
        if(instance != null){
                return instance;
        }

        dataTableMap.put(clazz,bean(clazz));

        return (T)  dataTableMap.get(clazz);
    }
    @SuppressWarnings("all")
    private static<T> T bean(Class clazz){
        return (T) applicationContext.getBean(clazz);
    }
}
