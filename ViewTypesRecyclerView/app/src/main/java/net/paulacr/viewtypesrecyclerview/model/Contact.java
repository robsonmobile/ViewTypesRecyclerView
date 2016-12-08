package net.paulacr.viewtypesrecyclerview.model;


public class Contact {

    private String name;
    private Integer icon;
    private Category category;

    public enum Category {
            FAMILY,
            FRIENDS,
            WORK }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
