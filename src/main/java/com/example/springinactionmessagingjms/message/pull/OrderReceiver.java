package com.example.springinactionmessagingjms.message.pull;

import com.example.springinactionmessagingjms.model.Taco;
import com.example.springinactionmessagingjms.model.TacoOrder;

import javax.jms.JMSException;

public interface OrderReceiver {

    TacoOrder receiveOrder() throws JMSException;

}
