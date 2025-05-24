package com.demo.testng.program.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserModel {
    @JsonProperty("email")
    private String email;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("department")
    private String department;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
