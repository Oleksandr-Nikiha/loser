package com.project.nikiha.loser.controller;

import org.springframework.web.bind.annotation.RestController;

import com.project.nikiha.loser.data.Response;
import com.project.nikiha.loser.service.CurrencyService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class MainController {
    private final CurrencyService currencyService;

    @GetMapping("currency/all")
    public Response getMethodName() {
        return new Response<>(currencyService.getCurrencies());
    }

}