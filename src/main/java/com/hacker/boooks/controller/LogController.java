package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Log;
import com.hacker.boooks.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for logs
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/logs")
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * @return logs
     * @apiNote get logs
     * @author [@thehackermonk]
     * @since 1.0
     */
//    @GetMapping
//    public List<Log> getLogs() {
//        return logService.getLogs();
//    }

}
