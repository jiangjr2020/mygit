package com.jjr.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * 功能说明：使用JDK代理类实现动态代理逻辑
 * 白话代理：代理点售火车票的过程：
 * （1）客户在买票前先买瓶水喝（代理点提供卖水的服务）；
 * （2）客户拿身份证买票（主要服务是卖火车票）
 * （3）客户买完火车票后，看到代售点还有彩票卖，于是又买了张彩票
 */
public class HelloProxy implements InvocationHandler {
    //定义一个目标对象
    public Object target = null;
    /**
     * bind方法说明：建立代理对象和目标对象的代理关系，并返回代理对象
     * @param target 目标对象
     * @return 代理对象
     */
    public Object bind(Object target){
        //保存目标对象实例
        this.target = target;
        //三个参数分别表示：
        // 1、目标对象的类加载器；
        // 2、目标对象的接口类；
        // 3、定义实现方法逻辑的代理类，必须实现了InvocationHandler的invoke方法
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    /**
     * 代理方法逻辑
     * @param proxy 代理对象，由bind方法生成
     * @param method 当前调度方法
     * @param args 当前方法参数
     * @return
     * @throws Throwable 异常
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理类：开始执行方法前，我先干点我自己的事，打印一个开始时间："+new Date().getTime());
        Object retObj = method.invoke(target,args);
        System.out.println("代理类：已经执行完方法，我再干点我自己的事，打印一个结束时间："+new Date().getTime());

        return retObj;
    }
}
