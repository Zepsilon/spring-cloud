package com.allianz.training.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableFeignClients
@EnableCircuitBreaker
public class ConsumerfeignApplication {

	@Autowired
	private IMyConsumer cons;
	
	public static void main(String[] args) {
		SpringApplication.run(ConsumerfeignApplication.class, args);
	}

    @RequestMapping(method = RequestMethod.GET, path = "/hystrixtest/{count}")
    @HystrixCommand(fallbackMethod="hystrixFallbackTest", 
    				commandProperties=@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="500"))
    public String hystrixTest(@PathVariable("count") int count) throws InterruptedException {
    	if (count == 5) {
    		throw new IllegalArgumentException();
    	}
    	Thread.sleep((long) count);
        return this.cons.serverInfo();

    }
    
    public String hystrixFallbackTest(int count) {
    	return "Problem var, "+count+" dk sonra dene.";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/produce")
    @HystrixCommand(fallbackMethod="callProducerFallbackTest", 
	commandProperties=@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="500"))
    public String callProducer() {
        return this.cons.serverInfo();

    }
    
    public String callProducerFallbackTest() {
    	return "Fallback "+ this.cons.serverInfo();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test/{name}/{surname}")
    public String callProducer(@PathVariable("name") String name, @PathVariable("surname") String surname) {
        return this.cons.test(name, surname);

    }
}
