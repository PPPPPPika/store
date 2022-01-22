package com.pika.store.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column("id")
    private Long id;
    @Column("usr")
    private Long usr;
    @Column("basket")
    private Long[] basket;
}