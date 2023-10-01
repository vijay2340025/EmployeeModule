package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public ResponseEntity<EmployeeDTO> fetchEmployee(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            Employee res = employee.get();
            EmployeeDTO employeeDTO = mapEmployeeResponse(res);
            return ResponseEntity.ok(employeeDTO);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new EmployeeDTO());
    }

    private static EmployeeDTO mapEmployeeResponse(Employee res) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(res.getId());
        employeeDTO.setName(res.getName());
        employeeDTO.setEmail(res.getEmail());
        employeeDTO.setDob(res.getDob());
        return employeeDTO;
    }

    public ResponseEntity<EmployeeDTO> add(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDob(employeeDTO.getDob());
        employee.setEmail(employeeDTO.getEmail());
        employeeRepo.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
