package ch.zhaw.gratisbrockibackend.domain.enums;

public enum Role {

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
}

