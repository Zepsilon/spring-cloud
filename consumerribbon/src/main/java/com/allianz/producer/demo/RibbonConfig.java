package com.allianz.producer.demo;

import org.springframework.context.annotation.Bean;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.NoOpPing;

public class RibbonConfig {

	@Bean
	public IPing ping() {
		//consumer'ların Producer'lara ping atma yöntemi bu metodla bildiriliyor
		return new NoOpPing();
	}
	
	public IRule rule() {
		return new AvailabilityFilteringRule();
	}
}
