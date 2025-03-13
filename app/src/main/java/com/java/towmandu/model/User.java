package com.java.towmandu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String fullName;
    private String email;
    private String password;
    private String phone;

}
