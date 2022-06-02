package ch.zhaw.gratisbrockibackend.domain.enums;

public enum Status {
    ACTIVE("Active"),
    ARCHIVED("Archived");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

