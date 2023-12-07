package com.javahabit.grandchildservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javahabit.grandchildservice.service.GcService;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GrandchildController {
	
	private static Logger logger = LoggerFactory.getLogger(GrandchildController.class);


    @Autowired
    GcService service;

    @GetMapping ("/grandchild")
    @Observed(
            name = "user.name",
            contextualName = "Grandchild-->service",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public String sayHi(){
        logger.info("Grandchild was called ...");
        return service.createHi();

    }


}
