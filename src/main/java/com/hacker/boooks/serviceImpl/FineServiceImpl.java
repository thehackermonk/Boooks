package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.FineEntity;
import com.hacker.boooks.repository.FineRepository;
import com.hacker.boooks.service.FineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@SuppressWarnings("unused")
public class FineServiceImpl implements FineService {

    Logger log = LoggerFactory.getLogger(FineServiceImpl.class);

    @Autowired
    private FineRepository fineRepository;

    /**
     * @return No. of days after which fine is charged
     * @apiNote No. of days after which fine is charged
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public int getDaysAfterFineIsCharged() {

        log.info(Response.DAYS_AFTER_WHICH_FINE_IS_CHARGED_FETCHED);

        return fineRepository.getDaysAfterFineIsCharged();

    }

    /**
     * @return Amount to be paid per day
     * @apiNote Amount to be paid per day
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public float getFineAmount() {

        log.info(Response.FINE_AMOUNT_FETCHED);

        return fineRepository.getFineAmount();

    }

    /**
     * @param days No. of days
     * @return true if no. of days updated successfully, false otherwise
     * @apiNote Set no. of days after which fine should be charged
     * @author [@thehackermonk]
     * @since 1.0
     */
    public Map<String, Boolean> setDaysForFine(int days) {

        FineEntity fineEntity = fineRepository.getById(1);
        fineEntity.setDaysForFine(days);

        fineRepository.save(fineEntity);

        log.info(Response.DAYS_AFTER_WHICH_FINE_IS_CHARGED_UPDATED);

        return Collections.singletonMap("result", true);

    }

    /**
     * @param amount Fine amount
     * @return true if amount updated successfully, false otherwise
     * @apiNote Set amount
     * @author [@thehackermonk]
     * @since 1.0
     */
    public Map<String, Boolean> setAmount(float amount) {

        FineEntity fineEntity = fineRepository.getById(1);
        fineEntity.setAmount(amount);

        fineRepository.save(fineEntity);

        log.info(Response.FINE_AMOUNT_UPDATED);

        return Collections.singletonMap("result", true);

    }

}
