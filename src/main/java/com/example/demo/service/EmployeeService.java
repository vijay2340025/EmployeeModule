package com.example.demo.service;

import com.example.demo.dto.EmployeeResponse;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public ResponseEntity<EmployeeResponse> fetchEmployee(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            Employee res = employee.get();
            EmployeeResponse employeeResponse = mapEmployeeResponse(res);
            return ResponseEntity.ok(employeeResponse);
        }
        return ResponseEntity.notFound().build();
    }

    private static EmployeeResponse mapEmployeeResponse(Employee res) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(res.getId());
        employeeResponse.setName(res.getName());
        employeeResponse.setEmail(res.getEmail());
        employeeResponse.setDob(res.getDob());
        return employeeResponse;
    }
}
