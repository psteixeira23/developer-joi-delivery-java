package com.tw.joi.delivery.controller;

import com.tw.joi.delivery.dto.response.LoadTestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadtest")
public class LoadTestController {

    @GetMapping("/ping")
    public ResponseEntity<LoadTestResponse> ping() {
        return ResponseEntity.ok(new LoadTestResponse("OK"));
    }
}
