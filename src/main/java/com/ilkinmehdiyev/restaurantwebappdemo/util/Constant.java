package com.ilkinmehdiyev.restaurantwebappdemo.util;

import java.util.List;

public class Constant {
    public static final String API_URL = "/api/v1";
    public static final String USER_NOT_FOUND_ERR_MESSAGE = "User with email %s not found.";
    public static final String USER_REGISTRATION = API_URL + "/registration";

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.trim().isEmpty();
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

}
