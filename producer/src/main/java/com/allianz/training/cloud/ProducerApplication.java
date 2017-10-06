package com.allianz.training.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RefreshScope
@RemoteApplicationEventScan
public class ProducerApplication {

	@Value("${server.port}")
	private String port;

	@Value("${test.message}")
	private String msg;

	public static void main(final String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/msg")
	public String showMsg() {
		return "Message: "+ this.msg;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/serverinfo")
	public String serverInfo() {
		return "My port : " + this.port;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/testfeign")
	public String serverInfo(@RequestParam("name") String name, @RequestParam("surname") String surname) {
		return "Hello : " + name + " " + surname;
	}
}
