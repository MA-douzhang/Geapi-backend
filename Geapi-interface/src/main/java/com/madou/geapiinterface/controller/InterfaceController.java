package com.madou.geapiinterface.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.madou.geapiclientsdk.model.LoveWords;
import com.madou.geapiclientsdk.model.User;

import com.madou.geapiinterface.mapper.LoveWordsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class InterfaceController {

    @Resource
    private LoveWordsMapper loveWordsMapper;

    @GetMapping("/get")
    public String getNameByGet(@RequestBody User user){
        return "GET 名字" + user.getUserName();
    }
    @PostMapping("/post")
    public String getNameByPost(@RequestBody User user){
        return "POST 名字" + user.getUserName();
    }
    @PostMapping("/user")
    public String getUserNameByGet(@RequestBody User user, HttpServletRequest request){
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
//        if (!accessKey.equals("madou")){
//            throw new RuntimeException("无权限");
//        }
//        if (Long.parseLong(nonce)>10000){
//            throw new RuntimeException("无权限");
//        }
//        //todo 时间和当前时间不能过五分钟
//
//        //配合Sdk中的密钥配置，形成加密 查询数据库中的密钥
//        String serverSign = SignUtils.getSign(body, "madou123");
//        if (!sign.equals(serverSign)){
//            throw new RuntimeException("无权限");
//        }
        String result = "POST 用户名字是" + user.getUserName();
        return result;
    }
    @GetMapping("/lovewords")
    public String getLoveWords(@RequestBody LoveWords loveWords){
        //todo 根据type返回不同格式的数据
        String loveWord = loveWordsMapper.getRandomLoveWords();
        return loveWord;
    }

    @GetMapping("/dygirl")
    public String getdyGirlGet(){
        Map<String,String> map= new HashMap<>();
        map.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.44");
        HttpResponse httpResponse = HttpRequest.get("https://zj.v.api.aa1.cn/api/video_dyv2")
                .addHeaders(map)
                .execute();
        String location = httpResponse.header("Location");
        HttpResponse httpResponse1 = HttpRequest.get(location).execute();
        return httpResponse1.body();
    }


}
