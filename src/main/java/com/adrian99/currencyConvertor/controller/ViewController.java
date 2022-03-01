package com.adrian99.currencyConvertor.controller;

import com.adrian99.currencyConvertor.convertor.Convertor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    private final Convertor convertor;

    public ViewController(Convertor convertor) {
        this.convertor = convertor;
    }

    @RequestMapping({"/","/index","index.html"})
    public String indexPage(Model model)
    {
        convertor.scraping();
        model.addAttribute("currencies", convertor.getCurrencies());
        return "index";
    }

}
