package com.demo.testng.program.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginUserResponse {
 
    @JsonProperty("token")
    private String token;

}
