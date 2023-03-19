package com.madou.geapi.utils;

import com.google.gson.Gson;
import com.madou.geapiclientsdk.client.GeapiClient;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    public String test(String name, int age) {

        return name + "，今年" + age + "岁，是一个极富好奇心地乐观青年！";
    }

    public Object reflectionInterface(Class<?> reflectionClass, String methodName, String parameter, Class<?> parameterType, String accessKey, String secretKey) {
        //构造反射类的实例
        Object result = null;
        try {
            Constructor<?> constructor = reflectionClass.getDeclaredConstructor(String.class, String.class);
            //获取SDK的实例，同时传入密钥
            GeapiClient geapiClient = (GeapiClient) constructor.newInstance(accessKey, secretKey);
            //获取SDK中所有的方法
            Method[] methods = geapiClient.getClass().getMethods();
            //筛选出调用方法
            for (Method method : methods
            ) {
                if (method.getName().equals(methodName)) {
                    //获取方法参数类型
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    //getMethod，多参会考虑重载情况获取方法,前端传来参数是JSON格式转换为String类型
                    Method method1 = geapiClient.getClass().getMethod(methodName, parameterTypes[0]);
                    //参数Josn化
                    Gson gson = new Gson();
                    Object args = gson.fromJson(parameter, parameterTypes[0]);
                    result = method1.invoke(geapiClient, args);
                }
            }
        } catch (Exception e) {
            log.error("反射调用参数错误",e);
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
