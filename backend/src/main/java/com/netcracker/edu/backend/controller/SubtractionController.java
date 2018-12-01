package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.constants.Constants;
import com.netcracker.edu.backend.service.SubtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subtraction")
public class SubtractionController {

    private SubtractionService subtractionService;

    @Autowired
    public SubtractionController(SubtractionService subtractionService) {
        this.subtractionService = subtractionService;
    }

    @RequestMapping(value = "/threshold/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") int threshold) {
        if (threshold < 0) {
            Constants.THRESHOLD = threshold;
//            System.out.println(subtractionService.getThreshold());
            return ResponseEntity.ok().build();
        } else {
//            System.out.println();
            return ResponseEntity.badRequest().build();
        }
    }

}
