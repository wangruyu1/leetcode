package com.canal.demo.controller;

import com.canal.demo.handler.feature.FeatureXHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private FeatureXHandler FeatureXHandler;

    @RequestMapping("/feature")
    public Object testQueryFeature(@RequestParam("id") long id) {
        return FeatureXHandler.getFeature(id);
    }
}
