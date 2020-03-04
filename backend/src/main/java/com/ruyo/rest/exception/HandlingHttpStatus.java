package com.ruyo.rest.exception;

import com.ruyo.rest.entity.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;


@ControllerAdvice
public class HandlingHttpStatus {

    public ResponseEntity ResponseFormatException(HttpStatus status, String message,
                                                  String error, HttpServletRequest request) {
        URL url;
        String path;
        try {
            url = new URL(request.getRequestURL().toString());
            path = url.getHost()+ url.getPath();
        } catch (MalformedURLException  | NullPointerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
        }
        return ResponseEntity.status(status).
                body(new ApiError(
                        new Timestamp(new Date().getTime()),
                        status,
                        message,
                        error,
                        path));
    }

}
