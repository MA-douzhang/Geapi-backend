package com.madou.geapigateway;

import com.madou.geapi.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableDubbo
@Service
public class GeapiGatewayApplication {
    //引入dubbo中的类
    @DubboReference
    private DemoService demoService;


    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(GeapiGatewayApplication.class, args);
        GeapiGatewayApplication application = context.getBean(GeapiGatewayApplication.class);
        String result = application.doSayHello("world");
        String result2 = application.doSayHello2("world");
        System.out.println("result: " + result);
        System.out.println("result: " + result2);


    }
    public String doSayHello(String name) {
        return demoService.sayHello(name);
    }

    public String doSayHello2(String name) {
        return demoService.sayHello2(name);
    }

}
