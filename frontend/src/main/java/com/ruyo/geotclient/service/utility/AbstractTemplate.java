package com.ruyo.geotclient.service.utility;

import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class AbstractTemplate {

    private final RestTemplate restTemplate;


    protected AbstractTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }


}
