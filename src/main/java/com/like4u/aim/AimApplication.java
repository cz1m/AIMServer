package com.like4u.aim;

import com.like4u.aim.service.AIMServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AimApplication {
    @Autowired
    private AIMServerService aimServerService;

    public static void main(String[] args) {

        SpringApplication.run(AimApplication.class, args);

    }



}
