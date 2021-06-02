package com.ilkinmehdiyev.restaurantwebappdemo.models.User;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserRole {
    ADMIN("admin", "admin"),
    USER("user", "user");

    private String name;
    private String description;

    UserRole(String name, String description) {
        this.name = "role.name." + name;
        this.description = "role.des." + description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static UserRole getByName(String name) {
        for (UserRole role : values()) {
            if (role.getName().equals(name))
                return role;
        }
        throw new IllegalStateException("Role" + name + " not found.");
    }
}
