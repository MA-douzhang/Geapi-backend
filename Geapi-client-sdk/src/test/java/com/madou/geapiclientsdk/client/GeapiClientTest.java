package com.madou.geapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.junit.jupiter.api.Test;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GeapiClientTest {

    @Test
    void getdyGirlGet() {
        Map<String,String> map= new HashMap<>();
        map.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.44");
        HttpResponse httpResponse = HttpRequest.get("https://zj.v.api.aa1.cn/api/video_dyv2")
                .addHeaders(map)
                .execute();
        String location = httpResponse.header("Location");
        HttpResponse httpResponse1 = HttpRequest.get(location).execute();

        System.out.println(httpResponse1);
    }
}
