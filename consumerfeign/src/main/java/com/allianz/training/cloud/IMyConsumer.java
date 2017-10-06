package com.allianz.training.cloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "producer")
public interface IMyConsumer {

    @RequestMapping(method = RequestMethod.GET, path = "/serverinfo")
    public String serverInfo();

    @RequestMapping(method = RequestMethod.GET, path = "/testfeign")
    public String test(@RequestParam("name") final String name,
                       @RequestParam("surname") final String surname);
}
