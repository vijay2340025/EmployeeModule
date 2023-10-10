package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ADDRESS-SERVICE")
public interface AddressFeignService {

    @GetMapping("/AddressClient/rpc/v1/address/{id}")
    public AddressDTO getAddressById(@PathVariable int id);

}
