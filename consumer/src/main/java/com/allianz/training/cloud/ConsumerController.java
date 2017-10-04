package com.allianz.training.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConsumerController {

    @Value("${my.message}")
    private String message;

    @RequestMapping(method = RequestMethod.GET, path = "/msg1")
    public String getMessageText(@Value("${my.message}") final String msg) {
        return "msg from function : " + msg + " msg from class : " + this.message;
    }

}
