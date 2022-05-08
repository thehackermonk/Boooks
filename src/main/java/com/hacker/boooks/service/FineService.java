package com.hacker.boooks.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface FineService {

    /**
     * @apiNote No. of days after which fine should be charged
     * @author [@thehackermonk]
     * @since 1.0
     */
    int getDaysAfterFineIsCharged();

    /**
     * @apiNote Fine per day
     * @author [@thehackermonk]
     * @since 1.0
     */
    float getFineAmount();

    /**
     * @apiNote Set no. of days after which fine should be charged
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> setDaysForFine(int days);

    /**
     * @apiNote Set amount
     * @author [@thehackermonk]
     * @since 1.0
     */
    Map<String, Boolean> setAmount(float amount);

}
