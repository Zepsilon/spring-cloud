package com.allianz.training.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ProducerApplication {

    @Value("${server.port}")
    private String port;

    @RequestMapping(method = RequestMethod.GET, path = "/serverinfo")
    public String serverInfo() {
        return "My port : " + this.port;
    }

    public static void main(final String[] args) {
        SpringApplication.run(ProducerApplication.class,
                              args);
    }
}
