package com.example.corbaclient.controller;

import com.example.corbaclient.corba.huawei.HuaweiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HuaweiCorbaController {

    @Autowired
    HuaweiClient huaweiClient;
    @GetMapping("/hello")
    public String hello() {

        return huaweiClient.getDetailCustomer("NameService");
    }
}
