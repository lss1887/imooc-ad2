package com.imooc.ad;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //调用其他微服务 或则监控
@SpringBootApplication
public class SponsorApplication {
}
