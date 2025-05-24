package com.demo.testng.program.response_model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddObjectResponse {
    
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
        private int price;

        @JsonProperty("cpu_model")
        private String cpuModel;

        @JsonProperty("hard_disk_size")
        private String hardDiskSize;

        @JsonProperty("color")
        private String color;

        @JsonProperty("capacity")
        private String capacity;

        @JsonProperty("screen_size")
        private String screenSize;
    }

}
