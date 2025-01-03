package com.consumer.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data-consumer")
public class DataConsumerController {

    @GetMapping
    public String status() {
        return "Message consumer is running.";
    }
}
