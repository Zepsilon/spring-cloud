package com.allianz.training.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin.server.EnableZipkinServer;

@EnableDiscoveryClient
@EnableZipkinServer
@SpringBootApplication
public class MonitorApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MonitorApplication.class,
                              args);
    }
}
