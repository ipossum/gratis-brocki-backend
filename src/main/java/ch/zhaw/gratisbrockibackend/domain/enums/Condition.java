package ch.zhaw.gratisbrockibackend.domain.enums;

public enum Condition {

    USED("Used"),
    NEW("New"),
    DEFECTIVE("Defective");

    private final String value;

    Condition(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

