package com.ruyo.geotclient.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorCustomController {

    @GetMapping("404")
    private String get404ErrorPage() {
        return "404";
    }

    @GetMapping("401")
    private String get401ErrorPage() {
        return "401";
    }

    @GetMapping("500")
    private String get500ErrorPage() {
        return "500";
    }

}
