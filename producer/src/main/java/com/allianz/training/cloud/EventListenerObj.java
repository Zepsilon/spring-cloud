package com.allianz.training.cloud;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerObj {

    @EventListener(MyCustomRemoteEvent.class)
    public void listen(final MyCustomRemoteEvent customRemoteEvent) {
        System.out.println(customRemoteEvent.getMessage());
    }

}
