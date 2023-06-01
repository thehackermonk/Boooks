package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Fine;
import com.hacker.boooks.service.FineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fine")
@Tag(name = "Fine Management", description = "APIs for managing fine")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@SuppressWarnings("unused")
public class FineController {

    @Autowired
    private FineService fineService;

    @GetMapping("")
    @Operation(summary = "Get Fine Details", description = "This API allows you to retrieve the current fine details configured in the library system. It returns the number of days after which fine should be collected and the amount to be collected as fine.")
    public ResponseEntity<Fine> getFineDetails() {
        log.info("Inside controller");
        return fineService.getFineDetails();
    }

    @PostMapping("/days")
    @Operation(summary = "Set Days for Fine", description = "This API allows you to retrieve the current fine details configured in the library system. It returns the number of days after which fine should be collected and the amount to be collected as fine.")
    public ResponseEntity<String> setDaysForFine(@RequestParam int days) {
        return fineService.setDaysForFine(days);
    }

    @PostMapping("/amount")
    @Operation(summary = "Set Amount for Fine", description = "This API allows you to set the amount to be collected as fine in the library system. It updates the configuration with the provided value.")
    public ResponseEntity<String> setAmountForFine(@RequestParam float amount) {
        return fineService.setAmountForFine(amount);
    }
}
