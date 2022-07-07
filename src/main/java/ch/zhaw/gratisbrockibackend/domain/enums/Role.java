package ch.zhaw.gratisbrockibackend.domain.enums;

import org.springframework.security.core.GrantedAuthority;

import java.util.*;

public enum Role implements GrantedAuthority {

    ADMIN("Administrator"),
    MODERATOR("Moderator"),
    USER("User");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getAuthority() {
        return "USER";
    }

    public Collection<GrantedAuthority> getAllowedOperations() {
        return new ArrayList<>(List.of(USER));
    }
}

