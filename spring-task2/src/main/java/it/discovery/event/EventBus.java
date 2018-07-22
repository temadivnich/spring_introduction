package it.discovery.event;

import it.discovery.log.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventBus {

    private final List<Logger> loggers;

    public EventBus(List<Logger> loggers) {
        this.loggers = loggers;
    }

    @EventListener
    public void logEventListener(LogEvent event) {
        loggers.forEach(logger -> logger.log(event.getMessage()));
    }
}
