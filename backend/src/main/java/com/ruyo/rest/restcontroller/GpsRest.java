package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.GpsDao;
import com.ruyo.rest.entity.Gps;
import com.ruyo.rest.exception.HandlingHttpStatus;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@UrlRestController
public class GpsRest  {

    private GpsDao gpsDaoFactory;
    private String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public GpsRest(GpsDao gpsDaoFactory) {
        this.gpsDaoFactory = gpsDaoFactory;
    }

    @GetMapping("/gps")
    public ResponseEntity getListGps(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Gps> empresas = gpsDaoFactory.getListGps();

        if (empresas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(empresas);
        }

    }

    @GetMapping("/gps_coord/{gps_id}")
    public ResponseEntity getGpsCoord(@PathVariable(name = "gps_id") long gps_id) {
        List gps = gpsDaoFactory.getCoordByGps(gps_id);
        if (gps == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + gps_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(gps);
        }
    }

    @GetMapping("/gps/{gps_id}")
    public ResponseEntity getGps(@PathVariable(name = "gps_id") long gps_id) {
        Optional<Gps> gps = gpsDaoFactory.getGps(gps_id);
        if (gps == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + gps_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(gps);
        }
    }

    @PostMapping("/gps")
    public ResponseEntity createGpsByVehiculo(Gps gps,
                                              @Nullable Long vehiculo_id,
                                              @Nullable Long celular_id, HttpServletRequest request) {
        Gps add = gpsDaoFactory.addGps(gps, vehiculo_id, celular_id);
        if(add != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(add);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "La empresa ya existe",
                    request);
        }
    }

    @DeleteMapping("/gps/{gps_id}")
    public ResponseEntity<Object> deleteGps(@PathVariable Long gps_id) {
        if (!gpsDaoFactory.delete(gps_id)) return new ResponseEntity<>
                (BODY + gps_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(gps_id, HttpStatus.OK);
    }

    @PutMapping("/gps/{gps_id}")
    public ResponseEntity<Object> updateGps(@PathVariable Long gps_id, @RequestBody Gps gps) {
        Gps update = gpsDaoFactory.update(gps_id, gps);
        if (null == update)return new ResponseEntity<>(BODY + gps_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }


}
