package com.adrian99.currencyConvertor.controller;

import com.adrian99.currencyConvertor.convertor.Convertor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConvertorController {

    private final Convertor convertor;

    public ConvertorController(Convertor convertor) {
        this.convertor = convertor;
    }

    @PostMapping("/api/convert")
    public Double convert(@RequestBody Map<String, String> currencies) {

        String firstCurrency = currencies.get("firstCurrency");
        String secondCurrency = currencies.get("secondCurrency");
        Double value = Double.valueOf(currencies.get("value"));

        return convertor.convert(firstCurrency, secondCurrency, value);
    }

}
