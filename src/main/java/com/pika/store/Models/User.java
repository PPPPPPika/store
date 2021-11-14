
package com.pika.store.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("password")
    private String password;
    
    /*@Column("role")
    private Role role;
    @Column("status")
    private Status status;*/
}

