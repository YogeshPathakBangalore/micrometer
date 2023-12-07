package com.example.childservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ChildController {
	
	private static Logger logger = LoggerFactory.getLogger(ChildController.class);

	final RestTemplate restTemplate;
    public ChildController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/child")
    @Observed(
            name = "user.name",
            contextualName = "child-->Grandchild",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public String sayHi()
    {
    	logger.info("Child was called ...");
    	logger.info("Calling Grandchild now ...");
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:5050/grandchild-svc/grandchild",
                HttpMethod.GET,
                null,
                String.class
        );
        String responseFromGrandChild = response.getBody();
        return responseFromGrandChild;
    }
}
