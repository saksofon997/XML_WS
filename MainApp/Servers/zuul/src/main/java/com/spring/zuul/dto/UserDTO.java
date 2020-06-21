package com.spring.zuul.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    //private String password;
    private String email;
    private String name;
    private String surname;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
}
