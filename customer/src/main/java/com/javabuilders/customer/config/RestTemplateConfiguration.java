package com.javabuilders.customer.config;

import com.javabuilders.customer.util.rest.PooledRestTemplateBuilder;
import com.javabuilders.customer.util.rest.RestTemplateProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Autowired
    private PooledRestTemplateBuilder pooledRestTemplateBuilder;

    @Bean
    @ConfigurationProperties(prefix = "rest.api.order")
    public RestTemplateProperties orderApiRestTemplateConfig() {
        return new RestTemplateProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "rest.api.product")
    public RestTemplateProperties productApiRestTemplateConfig() {
        return new RestTemplateProperties();
    }


    @Bean(name = "orderApiRestTemplate")
    public RestTemplate getOrderApiRestTemplate(@Qualifier("orderApiRestTemplateConfig")
                                                        RestTemplateProperties restTemplateProperties) {
        return pooledRestTemplateBuilder.with(restTemplateProperties)
                .build();

    }

    @Bean(name = "productApiRestTemplate")
    public RestTemplate getProductApiRestTemplate(@Qualifier("productApiRestTemplateConfig")
                                                        RestTemplateProperties restTemplateProperties) {
        return pooledRestTemplateBuilder.with(restTemplateProperties)
                .build();
    }

}
