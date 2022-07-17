package com.example.springinactionmessagingjms.message.pull;

import com.example.springinactionmessagingjms.model.TacoOrder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private final JmsTemplate jms;
    private final MessageConverter converter;
    private final Destination destination;

    public JmsOrderReceiver(JmsTemplate jms, MessageConverter converter, Destination destination) {
        this.jms = jms;
        this.converter = converter;
        this.destination = destination;
    }

    // Pull model: the client (JmsOrderReceiver) pulls from the queue if its ready but is then blocked until processed the receiveOrder method.

    @Override
    public TacoOrder receiveOrder() {
        return (TacoOrder) jms.receiveAndConvert(destination);
    }

    public TacoOrder receiveOrderAsMessageBeforeConversion() throws JMSException {
        Message message = jms.receive(destination);
        return (TacoOrder) converter.fromMessage(message);
    }

}
