package com.adrian99.currencyConvertor.convertor;

import java.util.List;

public interface Convertor {
    Double convert(String firstCurrency, String secondCurrency, Double value);
    void scraping();
    List<String> getCurrencies();
}
