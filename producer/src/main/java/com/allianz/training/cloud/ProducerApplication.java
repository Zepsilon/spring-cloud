package com.allianz.training.cloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
@EnableRabbit
@RemoteApplicationEventScan
public class ProducerApplication {

    @Autowired
    private ApplicationContext context;

    @Value("${server.port}")
    private String port;

    @Value("${test.message}")
    private String msg;

    @RequestMapping(method = RequestMethod.GET, path = "/msg")
    public String showMsg() {
        final String myUniqueId = this.context.getId(); // each service instance must have a unique context ID

        final MyCustomRemoteEvent event = new MyCustomRemoteEvent(this,
                                                                  myUniqueId,
                                                                  "hello world");
        this.context.publishEvent(event);
        return "Message : " + this.msg;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/serverinfo")
    public String serverInfo() {
        return "My port : " + this.port;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/testfeign")
    public String xyz(@RequestParam("name") final String name,
                      @RequestParam("surname") final String surname) {
        return "hello : " + name + " " + surname;
    }

    public static void main(final String[] args) {
        SpringApplication.run(ProducerApplication.class,
                              args);
    }
}
