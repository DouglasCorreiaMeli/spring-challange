package com.meli.socialMeli.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Buyer extends BaseEntity {

    @Column(nullable = false)
    private String userName;

    @Column
    @ManyToMany
    @JoinTable(
            name = "follows",
            joinColumns = @JoinColumn(name = "buyer_id"), inverseJoinColumns = @JoinColumn(name = "seller_id"))
    private List<Seller> sellers;
}
