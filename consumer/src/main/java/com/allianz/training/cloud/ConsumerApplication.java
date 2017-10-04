package com.allianz.training.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class ConsumerApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemp() {
        return new RestTemplate();
    }

    @Autowired
    private EurekaClient ec;

    @Autowired
    private RestTemplate rt;

    @RequestMapping(method = RequestMethod.GET, path = "/produce")
    public String callProducer() {
        InstanceInfo instanceInfo = this.ec.getNextServerFromEureka("producer",
                                                                    false);
        String homePageUrl = instanceInfo.getHomePageUrl() + "/serverinfo";
        return this.rt.getForObject(homePageUrl,
                                    String.class);

    }

    @RequestMapping(method = RequestMethod.GET, path = "/produce2")
    public String callProducer2() {
        return this.rt.getForObject("http://PRODUCER/serverinfo",
                                    String.class);

    }

    public static void main(final String[] args) {
        SpringApplication.run(ConsumerApplication.class,
                              args);
    }
}
