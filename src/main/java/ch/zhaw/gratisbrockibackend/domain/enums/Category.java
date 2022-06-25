package ch.zhaw.gratisbrockibackend.domain.enums;

public enum Category {
    CHILDREN("Children"),
    CLOTHING("Clothing"),
    GARDEN("Garden"),
    HOUSEHOLD("Household"),
    SPORT("Sport"),
    VEHICLE("Vehicle"),
    OTHER("Other");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
