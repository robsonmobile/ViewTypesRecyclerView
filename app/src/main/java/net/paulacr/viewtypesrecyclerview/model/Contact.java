package net.paulacr.viewtypesrecyclerview.model;


import java.util.Comparator;

public class Contact {

    private String name;
    private Integer icon;
    private Category category;
    public String headerTitle;

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

    public static class CompareContacts implements Comparator<Contact> {

        @Override
        public int compare(Contact first, Contact second) {
            if(first.name.compareTo(second.name) > 1) {
                return 1;
            } else if(first.name.compareTo(second.name) < 1) {
                return -1;
            } else {
                return 0;
            }
        }
    }


}
