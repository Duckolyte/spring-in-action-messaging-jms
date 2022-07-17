package com.example.springinactionmessagingjms.service;

import com.example.springinactionmessagingjms.model.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);

}
