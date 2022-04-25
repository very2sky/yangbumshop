package com.yangshop.yangbum.constant;


public enum Role {
    USER("USER"), ADMIN("ADMIN");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}