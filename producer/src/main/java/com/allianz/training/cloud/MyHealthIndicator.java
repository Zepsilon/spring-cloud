package com.allianz.training.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {

    @Value("${server.port}")
    private int port;

    @Override
    public Health health() {
        if (this.port == 7002) {
            return Health.down()
                         .build();
        }
        return Health.up()
                     .build();
    }

}
