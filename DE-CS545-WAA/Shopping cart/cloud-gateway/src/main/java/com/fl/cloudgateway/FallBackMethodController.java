package com.fl.cloudgateway;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/productServiceFallBack")
    public String productServiceFallBackMethod() {
        return "Product Service is taking longer that Expected." + "Please try again later";
    }

    @GetMapping("/categoryServiceFallBack")
    public String categoryServiceFallBackMethod() {
        return "Product Service is taking longer that Expected." + "Please try again later";
    }
}
