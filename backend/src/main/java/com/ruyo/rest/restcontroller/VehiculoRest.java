package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.VehiculoDao;
import com.ruyo.rest.entity.Vehiculo;
import com.ruyo.rest.exception.HandlingHttpStatus;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@UrlRestController
public class VehiculoRest {

    private final VehiculoDao vehiculoDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private final HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public VehiculoRest(VehiculoDao vehiculoDaoFactory){
        this.vehiculoDaoFactory = vehiculoDaoFactory;
    }

    @GetMapping("/vehiculo")
    public ResponseEntity getVehiculos(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Vehiculo> vehiculos = vehiculoDaoFactory.getVehiculos();
        for (Vehiculo vehiculo : vehiculos) {
            System.err.println(vehiculo.getMarca());
        }


        if (vehiculos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(vehiculos);
        }

    }

    @GetMapping("/vehiculo/{chofer_id}")
    public ResponseEntity getVehiculo(@PathVariable(name = "chofer_id") Long chofer_id) {
        Optional<Vehiculo> vehiculo = vehiculoDaoFactory.getVehiculo(chofer_id);
        if (!vehiculo.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + chofer_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(vehiculo);
        }
    }


    @GetMapping("/vehiculobygpsid/{chofer_id}")
    public ResponseEntity getVehiculoByIdGps(@PathVariable(name = "chofer_id") Long chofer_id) {
        Vehiculo vehiculoByGps = vehiculoDaoFactory.getVehiculoByGps(chofer_id);
        if (vehiculoByGps == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + chofer_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(vehiculoByGps);
        }
    }

    @PostMapping("/vehiculo")
    public ResponseEntity createVehiculo(Vehiculo vehiculo, HttpServletRequest request) {
        if(vehiculoDaoFactory.add(vehiculo) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(vehiculo);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }
    }

    @DeleteMapping("/vehiculo/{vehiculo_id}")
    public ResponseEntity<Object> deleteVehiculo(@PathVariable(name = "vehiculo_id") Long vehiculo_id) {
        if (!vehiculoDaoFactory.delete(vehiculo_id)) return new ResponseEntity<>
                (BODY + vehiculo_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(vehiculo_id, HttpStatus.OK);
    }

    @PutMapping(value = "/vehiculo/{vehiculo_id}")
    public ResponseEntity updateVehiculo(@PathVariable Long vehiculo_id,
                                         @RequestBody Vehiculo vehiculo) {
        vehiculo = vehiculoDaoFactory.update(vehiculo_id, vehiculo);
        if (vehiculo == null) return new ResponseEntity<>(BODY + vehiculo_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(vehiculo, HttpStatus.OK);

    }




}
