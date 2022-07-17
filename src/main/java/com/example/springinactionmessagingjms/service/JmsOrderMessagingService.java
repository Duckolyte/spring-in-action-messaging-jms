package com.example.springinactionmessagingjms.service;

import com.example.springinactionmessagingjms.config.JmsConfig;
import com.example.springinactionmessagingjms.model.TacoOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;
    private final JmsConfig config;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, JmsConfig config) {
        this.jms = jms;
        this.config = config;
    }

    // Examples using convertAndSend(). Framework makes conversion Object -> Message. Specify the object to be sent and optionally the destination.
    @Override
    public void sendOrder(TacoOrder order) {
        jms.convertAndSend(
                config.tacoOrderQueue(), // optional Destination
                order, // the TacoOrder to be converted and sent.
                message -> {
                    message.setStringProperty("X_ORDER_SOURCE", "WEB");
                    return message;
                }
        );
    }

    public void sendOrderWithMethodReference(TacoOrder order) {
        jms.convertAndSend(
                config.tacoOrderQueue(), // optional Destination
                order, // the TacoOrder to be converted and sent.
                this::addOrderSource // Method reference instead of lambda
        );
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

    public void sendOrderWithConvertAndSend(TacoOrder order) {
        jms.convertAndSend(
                config.tacoOrderQueue(), // optional Destination
                order); // the TacoOrder to be converted and sent.
    }

    // Examples without framework conversion.
    public void sendOrderUseDefault(TacoOrder order) {
        jms.send(session -> session.createObjectMessage(order)); // Use default destination from properties. spring.jms.template.default-destination
    }

    // The example methods below are just to show how to use the send method with another destination than the default one.
    public void sendOrderWithDestinationObject(TacoOrder order) {
        jms.send(
                config.tacoOrderQueue(), // Destination. Use default destination from properties if send is called without.
                session -> session.createObjectMessage(order));
    }

    public void sendOrderWithDestinationNameOnly(TacoOrder order) {
        jms.send(
                "tacocloud.order.queue", // Destination. Use default destination from properties if send is called without.
                session -> session.createObjectMessage(order));
    }

}
