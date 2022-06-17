package com.mepv.util;

import com.mepv.model.User;

import java.util.Optional;

public class StaticFunctions {

    public static Optional<User> loadUserByUsername(String username) {
        return Optional.ofNullable(User.find("username", username).firstResult());
    }
}
