package com.allianz.training.cloud;

import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.NoOpPing;

public class RibbonConfig {

    @Bean
    public IPing ping() {
        return new NoOpPing();
    }

    @Bean
    public IRule rule() {
        return new AvailabilityFilteringRule();
    }
}
