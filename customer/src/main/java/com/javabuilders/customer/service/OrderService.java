package com.javabuilders.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
public class OrderService {

    @Autowired
    @Qualifier("orderApiRestTemplate")
    private RestTemplate orderApiRestTemplate;

    @HystrixCommand(commandKey = "getCustomerOrderDetails", fallbackMethod = "fallbackCustomerOrderDetails")
    public String getCustomerOrderDetails(String id) {
        return orderApiRestTemplate.getForObject(format("http://localhost:8080/order/%s", id + "-b"), String.class);
    }

    public String fallbackCustomerOrderDetails(String id){
        return "fallback:: "+id;
    }
}
