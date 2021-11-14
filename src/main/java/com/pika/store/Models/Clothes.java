package com.pika.store.Models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.pika.store.Models.Enums.Genus;
import com.pika.store.Models.Enums.TypeClothes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

//@JsonAutoDetect
@Data
@Table("clothes")
@NoArgsConstructor
@AllArgsConstructor
public class Clothes {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("brand")
    private String brand;
    @Column("size")
    private String size;
    @Column("amount")
    private Integer amount;
    @Column("description")
    private String description;
    @Column("genus")
    private Genus genus;
    @Column("type")
    private TypeClothes type;
}
