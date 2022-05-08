package com.hacker.boooks.controller;

import com.hacker.boooks.bean.Log;
import com.hacker.boooks.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author [@thehackermonk]
 * @apiNote Controller class for logs
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/boooks")
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
    @GetMapping("/logs")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public List<Log> getLogs() {

        return logService.getLogs();

    }

}
