package com.allianz.training.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DirectoryApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DirectoryApplication.class,
                              args);
    }
}
