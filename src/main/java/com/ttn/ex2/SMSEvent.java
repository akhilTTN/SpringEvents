package com.ttn.ex2;

import org.springframework.context.ApplicationEvent;

public class SMSEvent extends ApplicationEvent{
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public SMSEvent(Object source) {
        super(source);
    }
}
