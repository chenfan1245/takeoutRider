package com.cykj.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
public class RiderController {

    @RequestMapping("/getOrder")
    public String getOrder(String latitude,String longitude){
        System.out.println(latitude+","+longitude);
        return "1";
    }

}
