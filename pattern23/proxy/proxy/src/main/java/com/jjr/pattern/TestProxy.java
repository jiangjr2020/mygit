package com.jjr.pattern; /**
 /*
 * 测试JKD的动态代理
 */

public class TestProxy {
    public static void main(String[] args){
        //JDK动态代理测试
        IHello hello = new Hello();
        IHello helloProxy = (IHello)new HelloProxy().bind(hello);
        helloProxy.run();
    }
}
