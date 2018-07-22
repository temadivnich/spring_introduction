package it.discovery.event;

import org.springframework.context.ApplicationEvent;

public class LogEvent extends ApplicationEvent {
    private final String message;

    public LogEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
