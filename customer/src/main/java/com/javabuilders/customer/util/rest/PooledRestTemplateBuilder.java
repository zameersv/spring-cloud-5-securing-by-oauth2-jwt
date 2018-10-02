package com.javabuilders.customer.util.rest;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PooledRestTemplateBuilder {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private RestTemplateProperties restTemplateProperties;

    public PooledRestTemplateBuilder with(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
        return this;
    }

    public RestTemplate build(){
        return restTemplateBuilder.requestFactory(this::getClientHttpRequestFactory)
                .setConnectTimeout(restTemplateProperties.getConnectionTimeout())
                .setReadTimeout(restTemplateProperties.getReadTimeout())
                .build();
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(restTemplateProperties.getPoolSize());

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }


}
