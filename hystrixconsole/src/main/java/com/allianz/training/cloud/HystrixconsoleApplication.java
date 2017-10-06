package com.allianz.training.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
public class HystrixconsoleApplication {

    public static void main(final String[] args) {
        SpringApplication.run(HystrixconsoleApplication.class,
                              args);
    }
}
