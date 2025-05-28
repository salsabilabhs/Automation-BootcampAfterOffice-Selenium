package com.demo.testng.program.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetListAllObjectResponse {
    
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("data")
    private DataDetail data;

    @Data
    public static class DataDetail {
        
        @JsonProperty("year")
        private String year;

        @JsonProperty("price")
        private Double price;

        @JsonProperty("CPU model")
        private String cpuModel;

        @JsonProperty("Hard disk size")
        private String hardDiskSize;

        @JsonProperty("color")
        private String color;

        @JsonProperty("capacity")
        private String capacity;

        @JsonProperty("screen_size")
        private String screenSize;
    }
}
