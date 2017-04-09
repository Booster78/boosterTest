package com.booster.boosterTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sorin on 08/04/2017.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    private RecordRepository repository;

    @Autowired
    public HomeController(RecordRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET,  produces="application/json")
    public @ResponseBody List<Record> home(ModelMap model) {
        System.out.print("GET home");
        List<Record> records = repository.findAll();
        model.addAttribute("records", records);
        model.addAttribute("insertRecord", new Record());
        return records;
    }

    @RequestMapping(method = RequestMethod.POST,  produces="application/json",consumes="application/json")
    public @ResponseBody
    List<Record> insertData(ModelMap model,
                            @ModelAttribute("insertRecord") @Valid Record record,
                            BindingResult result) {
        System.out.print("POST home");
        if (!result.hasErrors()) {
            repository.save(record);
        }
        return home(model);
    }
}
