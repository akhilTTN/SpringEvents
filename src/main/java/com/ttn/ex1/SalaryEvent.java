package com.ttn.ex1;

import org.springframework.context.ApplicationEvent;

public class SalaryEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public SalaryEvent(Object source) {
        super(source);
    }
}
