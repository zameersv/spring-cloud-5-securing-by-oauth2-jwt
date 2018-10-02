package com.javabuilders.customer.controller;

import com.javabuilders.customer.service.OrderService;
import com.javabuilders.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class CustomerController {

    private OrderService customerService;
    private ProductService productService;

    @Autowired
    public CustomerController(OrderService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @GetMapping("/customer/{id}")
    public String getCustomerDetails(@PathVariable String id){
        return id;
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping("/customer/{name}")
    public ResponseEntity<String> createCustomer(@PathVariable String name){
        return new ResponseEntity<String>("name", CREATED);
    }


}
