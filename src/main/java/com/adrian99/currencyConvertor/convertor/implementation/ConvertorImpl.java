package com.adrian99.currencyConvertor.convertor.implementation;

import com.adrian99.currencyConvertor.convertor.Convertor;
import org.springframework.stereotype.Component;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConvertorImpl implements Convertor {

    Map<String, Double> currencies = new HashMap<>();
    List<String> currenciesList = new ArrayList<>();
    @Override
    public Double convert(String firstCurrency, String secondCurrency, Double value) {

        double amount = value * currencies.get(firstCurrency);
        amount = amount / currencies.get(secondCurrency);

        amount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return amount;
    }

    @Override
    public void scraping() {
        try {
            Document document = Jsoup.connect("https://www.cursbnr.ro/").get();
            Elements products = document.select("div.table-responsive table tbody");
            for (Element e : products.select("tr")) {
                String symbol = e.select("td.text-center.hidden-xs").text();
                String valueTotal = e.select("td.text-center").text();
                String value = valueTotal.substring(valueTotal.indexOf(" ") + 1, valueTotal.indexOf(" ", valueTotal.indexOf(" ") + 1));
                String name = e.select("tr td a").text();
                currencies.put(symbol, Double.valueOf(value));
//              currenciesList.add("<option value = \""+symbol+"\">"+symbol+"</option>");
                currenciesList.add(symbol);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
        currencies.put("RON", 1D);
        currenciesList.add("RON");
    }

    public List<String> getCurrencies() {
        return currenciesList;
    }
}

