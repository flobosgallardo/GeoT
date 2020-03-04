package com.ruyo.rest.restcontroller;

import com.ruyo.rest.restcontroller.utility.CalculoCombustible;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@UrlRestController
public class CalculoCombustibleRest extends CalculoCombustible {

    @GetMapping("/calcomb")
    public ResponseEntity getCalculoCombustible(@RequestParam double distancia,
                                                @RequestParam double rendimiento,
                                                @RequestParam double pCombustible){
        int calculo = getCalComb(distancia, rendimiento, pCombustible);
        if(calculo != 0) return ResponseEntity.status(HttpStatus.OK).body(calculo);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No es posible calcular");
    }
}
