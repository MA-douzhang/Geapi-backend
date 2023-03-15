package com.madou.geapiinterface.controller;

import com.madou.geapiclientsdk.model.User;
import com.madou.geapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name){
        return "GET 名字" + name;
    }
    @PostMapping("/")
    public String getNameByPost(@RequestParam String name){
        return "POST 名字" + name;
    }
    @PostMapping("/user")
    public String getUserNameByGet(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        if (!accessKey.equals("madou")){
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce)>10000){
            throw new RuntimeException("无权限");
        }
        //todo 时间和当前时间不能过五分钟

        //配合Sdk中的密钥配置，形成加密 查询数据库中的密钥
        String serverSign = SignUtils.getSign(body, "madou123");
        if (!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }
        return "GET 名字" + user.getUserName();
    }
}
