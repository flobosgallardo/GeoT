package com.ruyo.rest.restcontroller;


import com.ruyo.rest.exception.HandlingHttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexRest {

    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @GetMapping(value = "/**")
    public ResponseEntity method(HttpServletRequest request) {
        return handlingHttpStatus.ResponseFormatException(
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.toString(),
                "El recurso solicitado no encontrado",
                request);

    }


}
