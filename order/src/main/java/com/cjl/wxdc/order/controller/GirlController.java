package com.cjl.wxdc.order.controller;

import com.cjl.wxdc.order.config.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/girl")
public class GirlController
{
    @Autowired
    private Girl girl;
    @GetMapping("/get")
    public Object get()
    {
        return girl.getName()+":"+girl.getAge();
    }
}
