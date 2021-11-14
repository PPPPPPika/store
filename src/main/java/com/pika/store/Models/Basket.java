package com.pika.store.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Data
@Table("baskets")
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @Column("id")
    private Long id;

    @Transient
    @Column("items")
    private List<Clothes> items;

    @Column("owner")
    private User user;

}
