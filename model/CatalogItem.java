package model;

public class CatalogItem {
    private String name, description, category, type;

    private double price;

    public CatalogItem(String name, double price, String description, String category, String type) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
