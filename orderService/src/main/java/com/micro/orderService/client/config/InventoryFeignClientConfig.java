package com.micro.orderService.client.config;

import com.micro.orderService.exception.CustomErrorDecoder;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class InventoryFeignClientConfig {
    @Bean
    public Logger.Level feignInvLoggerLevel(){
        return Logger.Level.FULL;
    }
//    @Bean
//    public Request.Options options(){
//        return new Request.Options(Duration.ofMillis(3000),Duration.ofMillis(5000), options().isFollowRedirects());
//    }
    @Bean
    public Retryer retryer(){
        return new Retryer.Default(1L,2L,3);
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("x-correlation-Id", UUID.randomUUID().toString());
        };
    }
    public CustomErrorDecoder ErrorDecoder(){
        return new CustomErrorDecoder();
    }

}
