package com.madou.geapiinterface;

import com.madou.geapiclientsdk.client.GeapiClient;
import com.madou.geapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@SpringBootTest
class GeapiInterfaceApplicationTests {

    @Resource
    GeapiClient geapiClient;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUserName("madou");
        String madouGet = geapiClient.getNameByGet("madouGet");
        String userNameByPost = geapiClient.getUserNameByPost(user);
        System.out.println(madouGet);
        System.out.println(userNameByPost);
    }

}
