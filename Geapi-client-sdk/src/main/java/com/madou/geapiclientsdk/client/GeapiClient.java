package com.madou.geapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.madou.geapiclientsdk.model.LoveWords;
import com.madou.geapiclientsdk.model.User;
import com.madou.geapiclientsdk.utils.SignUtils;


import java.util.HashMap;
import java.util.Map;


public class GeapiClient {

    private static final String GATEWAY_HOST = "http://localhost:8091";

    private String accessKey;
    private String secretKey;

    public GeapiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(User user){
        String json = JSONUtil.toJsonStr(user);
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HttpResponse httpResponse= HttpRequest.get(GATEWAY_HOST+"/api/get")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = httpResponse.body();
        return result;
    }
    public String getNameByPost(User user){
        //该方法加了@RequestParam接收JSON
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse= HttpRequest.post(GATEWAY_HOST+"/api/post")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = httpResponse.body();
        return result;
    }
    //模拟接口
    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/user")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        System.out.println(result);
        return result;
    }

    //随机情话接口
    public String getLoveWordsGet(LoveWords loveWords) {
        String json = JSONUtil.toJsonStr(loveWords);
        HttpResponse httpResponse = HttpRequest.get(GATEWAY_HOST + "/api/lovewords")
                .addHeaders(getHeaderMap(json))
                .body(json)
                .execute();
        String result = httpResponse.body();
        return result;
    }

    //添加请求头，发送给网关校验用户的信息
    private Map<String, String> getHeaderMap(String body) {
        Map<String,String> map = new HashMap<>();
        map.put("accessKey",accessKey);

        //map.put("secretKey",secretKey);
        map.put("nonce", RandomUtil.randomNumbers(4));
        map.put("body",body);
        map.put("timestamp",String.valueOf(System.currentTimeMillis()/1000));
        map.put("sign", SignUtils.getSign(body,secretKey));
        return map;
    }


}
