package com.demo.testng.program.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class ObjectModel {

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

        @JsonProperty("cpu_model")
        private String cpuModel;

        @JsonProperty("hard_disk_size")
        private String hardDiskSize;

        @JsonProperty("capacity")
        private String capacity;

        @JsonProperty("screen_size")
        private String screenSize;

        @JsonProperty("color")
        private String color;
    }
    
}
