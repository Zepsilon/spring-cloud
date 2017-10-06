package com.allianz.training.cloud;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class MyCustomRemoteEvent extends RemoteApplicationEvent {

    private String message;
    /**
     * 
     */
    private static final long serialVersionUID = 5891069522735671177L;

    public MyCustomRemoteEvent() {
    }

    public MyCustomRemoteEvent(final Object source, final String originService, final String message) {
        // source is the object that is publishing the event
        // originService is the unique context ID of the publisher
        super(source,
              originService);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public MyCustomRemoteEvent setMessage(final String message) {
        this.message = message;
        return this;
    }
}
