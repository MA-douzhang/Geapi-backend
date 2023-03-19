package com.madou.geapiclientsdk;

import com.madou.geapiclientsdk.client.GeapiClient;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("geapi.client")
@Data
@ComponentScan
public class GeapiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public GeapiClient geapiClient(){
        return new GeapiClient(accessKey,secretKey);
    }
}
