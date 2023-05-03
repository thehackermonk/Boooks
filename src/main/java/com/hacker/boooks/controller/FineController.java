package com.hacker.boooks.controller;

import com.hacker.boooks.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for everything related to fine
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/fine")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class FineController {

    @Autowired
    private FineService fineService;

    /**
     * @param days no. of days after which member should be fined
     * @return true if days added successfully, false otherwise
     * @apiNote update no. of days after which member should be fined
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PutMapping("/days")
//    public Map<String, Boolean> setDaysForFine(@RequestHeader int days) {
//
//        return fineService.setDaysForFine(days);
//
//    }

    /**
     * @param amount amount to be fined per day
     * @return true if days added successfully, false otherwise
     * @apiNote update amount to be fined per day
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @PutMapping("/amount")
//    public Map<String, Boolean> setAmount(@RequestHeader float amount) {
//
//        return fineService.setAmount(amount);
//
//    }

}
