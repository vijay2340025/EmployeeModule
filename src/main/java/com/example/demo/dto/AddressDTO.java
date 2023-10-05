package com.example.demo.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private int id;
    private String houseNo;
    private String streetName;
    private String zipCode;
    private String country;
}
