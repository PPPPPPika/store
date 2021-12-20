package com.pika.store.Models;

import com.fasterxml.jackson.annotation.JsonView;
import com.pika.store.Configuration.ViewConfiguration;
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
    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @Id
    @Column("id")
    private Long id;

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @Column("email")
    private String email;

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @Column("name")
    private String name;

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Column("password")
    private String password;

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @Column("number")
    private String number;

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @Column("address")
    private String address;

    @JsonView(ViewConfiguration.PERMITTED_DATA.class)
    @Column("role")
    private Role role;

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Override
    public String getUsername() {
        return email;
    }

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonView(ViewConfiguration.EXCLUDED_FIELD.class)
    @Override
    public boolean isEnabled() {
        return false;
    }
}

