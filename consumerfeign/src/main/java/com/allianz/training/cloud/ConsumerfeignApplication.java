package com.allianz.training.cloud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
@EnableRabbit
public class ConsumerfeignApplication {

    @Autowired
    private RabbitTemplate rt;

    @Autowired
    private IMyConsumer cons;

    @RequestMapping(method = RequestMethod.GET, path = "/rabbit")
    public String sendMessageToRabbitMQ() {
        this.rt.convertAndSend("myexchange",
                               "producer",
                               "Hello from consumerfeign");
        return "OK";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/produce")
    @HystrixCommand(fallbackMethod = "callProducerFallback", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"))
    public String callProducer() {
        return this.cons.serverInfo();
    }

    public String callProducerFallback() {
        return this.cons.serverInfo();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hystrixtest/{count}")
    @HystrixCommand(fallbackMethod = "hystrixFallbackTest", commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"))
    public String hystrixTest(@PathVariable("count") final int count) throws IllegalAccessException,
                                                                      InterruptedException {
        if (count == 5) {
            throw new IllegalAccessException();
        }
        Thread.sleep(count);
        return this.cons.serverInfo();
    }

    public String hystrixFallbackTest(final int count) {
        return "Problem var. " + count + " dak sonra gene dene";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/test/{name}/{surname}")
    public String callProducer(@PathVariable("name") final String name,
                               @PathVariable("surname") final String surname) {
        return this.cons.test(name,
                              surname);
    }

    public static void main(final String[] args) {
        SpringApplication.run(ConsumerfeignApplication.class,
                              args);
    }
}
