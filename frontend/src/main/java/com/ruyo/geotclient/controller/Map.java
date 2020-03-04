package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.CustomSession;
import com.ruyo.geotclient.model.Cliente;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.concurrent.Future;

@Controller

public class Map extends CustomSession {

    public Map(HttpSession session) {
        super(session);
    }

    @GetMapping("/map")
    @Async
    public ListenableFuture<String> getMap(){

            return AsyncResult.forValue("map");

    }

}
