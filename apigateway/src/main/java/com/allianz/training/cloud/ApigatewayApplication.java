package com.allianz.training.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ApigatewayApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApigatewayApplication.class,
                              args);
    }
}
