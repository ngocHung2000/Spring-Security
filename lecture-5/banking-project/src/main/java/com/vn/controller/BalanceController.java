package com.vn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class BalanceController {
    @GetMapping("/myBalance")
    public String getBlanceDetails(){
        return "Here are the balance details from the DB";
    }
}
