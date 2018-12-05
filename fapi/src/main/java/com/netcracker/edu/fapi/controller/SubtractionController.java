package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.Constants;
import com.netcracker.edu.fapi.service.SubtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyAuthority('admin')")
@RequestMapping("/api/subtraction")
public class SubtractionController {

    @Autowired
    private SubtractionService subtractionService;

    @RequestMapping(value = "/threshold/{value}", method = RequestMethod.PUT)
    public ResponseEntity<?> editThreshold(@PathVariable(name = "value") Integer threshold) {
        if (threshold < 0) {
            Constants.THRESHOLD = threshold;
            subtractionService.editThreshold(Constants.THRESHOLD);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/threshold")
    public ResponseEntity<Integer> getThreshold() {
            return ResponseEntity.ok(Constants.THRESHOLD);
    }

}
