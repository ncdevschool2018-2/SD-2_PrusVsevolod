package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subtraction")
public class SubtractionController {


    @Autowired
    public SubtractionController() {
    }

    @RequestMapping(value = "/threshold/{value}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> editThreshold(@PathVariable(name = "value") Integer threshold) {
        if (threshold < 0) {
            Constants.THRESHOLD = threshold;
            return ResponseEntity.ok(Constants.THRESHOLD);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
