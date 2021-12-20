package com.pika.store.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pika.store.Models.Enums.SecurityEnums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column("id")
    private Long id;
    @Column("email")
    private String email;
    @Column("name")
    private String name;
    @Column("password")
    private String password;
    @Column("number")
    private String number;
    @Column("address")
    private String address;
    @Column("role")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

