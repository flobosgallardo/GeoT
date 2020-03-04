package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.CoordenadaDao;
import com.ruyo.rest.entity.Coordenada;
import com.ruyo.rest.exception.HandlingHttpStatus;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@UrlRestController
@CrossOrigin
public class CoordenadaRest {

    private CoordenadaDao coordenadaDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public CoordenadaRest(@Qualifier("coordenadaDao") CoordenadaDao coordenadaDaoFactory) {
        this.coordenadaDaoFactory = coordenadaDaoFactory;
    }


    @PostMapping("/coordenada")
    public ResponseEntity createCoordenada(@RequestBody Coordenada coordenada, HttpServletRequest request) {
        if(coordenadaDaoFactory.add(coordenada) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(coordenada);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.CONFLICT,
                    HttpStatus.CONFLICT.toString(),
                    HttpStatus.CONFLICT.toString() ,
                    request);
        }
    }

    @GetMapping("/coordenadas/{gps_id}")
    public ResponseEntity<Object> getCoordenada(@PathVariable Long gps_id) {
        List coordenadaByIdGps = coordenadaDaoFactory.getCoordenadaByIdGps(gps_id);
        if (null == coordenadaByIdGps)return new ResponseEntity<>(BODY + gps_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(coordenadaByIdGps, HttpStatus.OK);
    }


}
