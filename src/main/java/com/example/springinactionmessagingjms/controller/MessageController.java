package com.example.springinactionmessagingjms.controller;


import com.example.springinactionmessagingjms.model.TacoOrder;
import com.example.springinactionmessagingjms.repository.TacoOrderRepository;
import com.example.springinactionmessagingjms.service.OrderMessagingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/messages", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:8080")
public class MessageController {

    private final TacoOrderRepository repository;
    private final OrderMessagingService messagingService;

    public MessageController(TacoOrderRepository repository, OrderMessagingService messagingService) {
        this.repository = repository;
        this.messagingService = messagingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> getTacoOrderMessageById(@PathVariable Long id) {
        Optional<TacoOrder> order = repository.findById(id);
        return order.map(tacoOrder -> new ResponseEntity<>(tacoOrder, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        messagingService.sendOrder(order);
        return repository.save(order);
    }
}
