package com.example.springinactionmessagingjms.repository;


import com.example.springinactionmessagingjms.model.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
    // The position of the argument must match the predicate in the method signatur
    // e.g. readOrdersByDeliveryZipAndPlacedAtBetween(
    //            String deliveryZip, Date startDate, Date endDate) -> deliveryZip = arg1, Between expects 2 args -> startDate=arg2 and endDate=arg3.
    List<TacoOrder> findByDeliveryZip(String deliveryZip); // Custom access method to get orders by zip code.
    int countByDeliveryZip(String deliveryZip); // Custom access method to get the number of orders by zip code.
    List<TacoOrder> findByDeliveryZipAndDeliveryStreet(String deliveryZip, String deliveryStreet); // Custom access method to get orders by zip code and street.
    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(
            String deliveryZip, Date startDate, Date endDate); // Get orders by zip and that were placed between start and end date.
    List<TacoOrder> findByDeliveryToAndDeliveryCityIgnoreCase(
            String deliveryTo, String deliveryCity); // Ignore case when query
    List<TacoOrder> findByDeliveryCityOrderByDeliveryTo(String city); // Order by city

    // if method names get too long use the @Query annotation to write the own query
    @Query("from TacoOrder o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();

}
