package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.CelularDao;
import com.ruyo.rest.entity.Celular;
import com.ruyo.rest.exception.HandlingHttpStatus;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@UrlRestController
public class CelularRest {

    private CelularDao celularDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public CelularRest(CelularDao celularDaoFactory){
        this.celularDaoFactory = celularDaoFactory;
    }

    @GetMapping("/celular")
    public ResponseEntity getCelulares(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Celular> celulares = celularDaoFactory.getCelulares();
        if (celulares.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(celulares);
        }

    }

    @GetMapping("/celular/{celular_id}")
    public ResponseEntity getCelular(@PathVariable(name = "celular_id") Long celular_id) {
        Optional<Celular> celular = celularDaoFactory.getCelular(celular_id);
        if (!celular.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + celular_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(celular);
        }
    }


    @GetMapping("/celbyclient/{celular_id}")
    public ResponseEntity getCelularByClienteId(@PathVariable(name = "celular_id") Long celular_id) {
        List celularesByIdCliente = celularDaoFactory.getCelularesByIdCliente(celular_id);
        if (celularesByIdCliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + celular_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(celularesByIdCliente);
        }
    }

    @PostMapping("/celular")
    public ResponseEntity createCelular(@RequestBody Celular celular, HttpServletRequest request, HttpHeaders headers) {
        if(celularDaoFactory.add(celular) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(celular);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }
    }

    @DeleteMapping("/celular/{celular_id}")
    public ResponseEntity<Object> deleteCelular(@PathVariable(name = "celular_id") Long cliente_id) {
        if (!celularDaoFactory.delete(cliente_id)) return new ResponseEntity<>
                (BODY + cliente_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cliente_id, HttpStatus.OK);
    }

    @PutMapping(value = "/celular/{celular_id}")
    public ResponseEntity updateCelular(@PathVariable Long celular_id,
                                        @RequestBody Celular celular) {
        celular = celularDaoFactory.update(celular_id, celular);
        if (null == celular)return new ResponseEntity<>(BODY + celular_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(celular, HttpStatus.OK);
    }
}
