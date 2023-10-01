package com.example.demo.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private String streetName;
    private String streetNumber;
    private String country;
    private String zipcode;
}
