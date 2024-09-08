package com.thedevjournal.mystocks.security;

import com.thedevjournal.mystocks.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class AppUserDetails implements UserDetails {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static AppUserDetails build(User user) {
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toSet());

        return new AppUserDetails(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
