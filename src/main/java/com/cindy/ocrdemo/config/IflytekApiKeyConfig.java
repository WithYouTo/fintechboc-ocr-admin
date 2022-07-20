package com.cindy.ocrdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "iflytek")
public class IflytekApiKeyConfig {
   private  String requestUrl;
   private  String appid;
    //请填写控制台获取的APISecret;
   private  String apiSecret;
    //请填写控制台获取的APIKey
   private  String apiKey;
}
