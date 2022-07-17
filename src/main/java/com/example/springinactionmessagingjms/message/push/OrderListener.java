package com.example.springinactionmessagingjms.message.push;

import com.example.springinactionmessagingjms.model.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Profile("jms-listener")
@Component
@Slf4j
public class OrderListener {

    @JmsListener(destination = "tacocloud.order.queue") // Triggers as soon as a new message is in the queue.
    public void receiveOrder(TacoOrder order) {
        log.info(order.toString());
    }

}
