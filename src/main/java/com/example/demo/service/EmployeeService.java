package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    @Lazy
    DiscoveryClient discoveryClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplateBuilder builder;

    @Autowired
    private AddressFeignService addressFeignService;

    public ResponseEntity<EmployeeDTO> fetchEmployee(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            Employee res = employee.get();
            EmployeeDTO employeeDTO = mapEmployeeResponse(res);
            return ResponseEntity.ok(employeeDTO);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new EmployeeDTO());
    }

    private EmployeeDTO mapEmployeeResponse(Employee res) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        AddressDTO addressDTO = new AddressDTO();

        employeeDTO.setId(res.getId());
        employeeDTO.setName(res.getName());
        employeeDTO.setEmail(res.getEmail());
        employeeDTO.setDob(res.getDob());

        try {
            /*ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");
            String uri = serviceInstance.getUri().toString();
            addressDTO = builder.build().getForObject(uri + "/AddressClient/rpc/v1/address/12", AddressDTO.class);*/
            addressDTO = addressFeignService.getAddressById(12);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        employeeDTO.setAddressDTO(addressDTO);
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
