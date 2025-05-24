package com.demo.testng.program.response_model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterUserResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("department")
    private String department;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("create_at")
    private String createdAt;

    @JsonProperty("update_at")
    private String updatedAt;

}