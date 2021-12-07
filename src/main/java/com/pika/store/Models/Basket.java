package com.pika.store.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("baskets")
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    @Id
    @Column("id")
    private Long id;
    @Column("owner")
    private Long user;
    @Column("item")
    private Long items;
}
