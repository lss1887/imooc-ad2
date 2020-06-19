package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//使用feign 访问其他微服务
@EnableFeignClients
@EnableEurekaClient
//断路器
@EnableHystrix
//断路器
@EnableCircuitBreaker
// 被发现
@EnableDiscoveryClient
//hystrix 监控
@EnableHystrixDashboard
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }

    //    轮询负载均衡
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();

    }
}
