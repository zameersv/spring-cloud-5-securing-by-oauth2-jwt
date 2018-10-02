package com.javabuilders.customer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
public class ProductService {

    @Autowired
    @Qualifier("productApiRestTemplate")
    private RestTemplate productApiRestTemplateConfig;

    @HystrixCommand(commandKey = "getCustomerProductRecommendation")
    public String getCustomerProductRecommendation(String id) {
        return productApiRestTemplateConfig.getForObject(format("http://localhost:8080/product/recommendation/%s", id), String.class);
    }

}
