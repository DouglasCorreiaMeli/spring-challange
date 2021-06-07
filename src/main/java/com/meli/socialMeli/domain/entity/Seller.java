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
public class Seller extends BaseEntity {

    @Column(nullable = false)
    String userName;

    @Column
    @ManyToMany(mappedBy = "sellers")
    private List <Buyer> buyers;

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    private List <Publication> publications;
}
