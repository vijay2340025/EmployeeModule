package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.AddressFeignService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class FrontController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "id") int eid) {
        return employeeService.fetchEmployee(eid);
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.add(employeeDTO);
    }
}
