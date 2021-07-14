package com.library.authentication.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
public class AppUserDetail {

    private final Long appUserId;

    private final String username;

    private final boolean enabled;

    private final Set<GrantedAuthority> authorities;

    public AppUserDetail(User user) {
        this.appUserId = user.getId();
        this.username = user.getUsername();
        this.authorities = Set.of(new SimpleGrantedAuthority(user.getAuthority()));
        this.enabled = Objects.requireNonNullElse(user.getEnabled(), false);
    }
}
