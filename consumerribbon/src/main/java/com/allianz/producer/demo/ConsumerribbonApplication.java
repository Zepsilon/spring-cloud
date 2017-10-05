package com.allianz.producer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableDiscoveryClient // hata olusturuyordu, kaldirdik
@RibbonClient(name = "client1", configuration = RibbonConfig.class)
@RestController
public class ConsumerribbonApplication {

	@Autowired
	private RestTemplate rt;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemp() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerribbonApplication.class, args);
	}

    @RequestMapping(method = RequestMethod.GET, path = "/produce")
    public String callProducer() {
        return this.rt.getForObject("http://client1/serverinfo",
                                    String.class);

    }
}
