package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class FrontController {

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable(name = "id") String eid) {
        return null;
    }
}
