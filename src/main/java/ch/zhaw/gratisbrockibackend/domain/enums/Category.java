package ch.zhaw.gratisbrockibackend.domain.enums;

public enum Category {
    ChildrenItemCategory("ChildrenItemCategory"),
    ClothingItemCategory("ClothingItemCategory"),
    GardenItemCategory("GardenItemCategory"),
    HouseholdItemCategory("HouseholdItemCategory"),
    SportItemCategory("SportItemCategory"),
    VehicleItemCategory("VehicleItemCategory"),
    OtherItemCategory("OtherItemCategory");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
