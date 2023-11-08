package com.TddSportsApp.controller;

import com.TddSportsApp.service.ResultService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
public class ResultController {
    ResultService resultService;


}
