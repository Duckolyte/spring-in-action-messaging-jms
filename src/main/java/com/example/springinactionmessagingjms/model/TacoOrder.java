package com.example.springinactionmessagingjms.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String deliveryZip;
    private String deliveryStreet;
    private String deliveryTo;
    private String deliveryCity;
    private Date placedAt = new Date();

    @OneToMany(cascade = CascadeType.ALL) // Delete all taco references related to this order if the order gets deleted.
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
