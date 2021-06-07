package com.meli.socialMeli.domain.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Publication extends BaseEntity{

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    Product product;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private double price;

    @Column
    private Boolean hasPromo;

    @Column
    private Double discount;

}
