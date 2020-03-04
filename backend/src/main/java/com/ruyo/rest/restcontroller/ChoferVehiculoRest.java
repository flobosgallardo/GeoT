package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.ChoferVehiculoDao;
import com.ruyo.rest.entity.ChoferVehiculo;
import com.ruyo.rest.exception.HandlingHttpStatus;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@UrlRestController
public class ChoferVehiculoRest {

    private ChoferVehiculoDao choferVehiculoDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public ChoferVehiculoRest(ChoferVehiculoDao choferVehiculoDaoFactory) {
        this.choferVehiculoDaoFactory = choferVehiculoDaoFactory;
    }

    @GetMapping("/chlo")
    public ResponseEntity getChoferVehiculos(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        List<ChoferVehiculo> clientes = choferVehiculoDaoFactory.getChoferVehiculo();

        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(clientes);
        }

    }

    @GetMapping("/chlo/{chlo_id}")
    public ResponseEntity getChoferVehiculo(@PathVariable(name = "chlo_id") long chlo_id) {
        Optional<ChoferVehiculo> cliente = choferVehiculoDaoFactory.getChoferVehiculo(chlo_id);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + chlo_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
    }

    @PostMapping("/chlo")
    public ResponseEntity createChoferVehiculo(@RequestBody ChoferVehiculo choferVehiculo, HttpServletRequest request) {
        if(choferVehiculoDaoFactory.addChoferVehiculo(choferVehiculo) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(choferVehiculo);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }
    }

    @DeleteMapping("/chlo/{chlo_id}")
    public ResponseEntity deleteChoferVehiculo(@PathVariable Long chlo_id) {
        if (!choferVehiculoDaoFactory.deleteChoferVehiculo(chlo_id)) return new ResponseEntity<>
                (BODY + chlo_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chlo_id, HttpStatus.OK);
    }

    @PutMapping("/chofer/{chlo_id}")
    public ResponseEntity<Object> updateChofer(@PathVariable Long chlo_id, @RequestBody ChoferVehiculo chofer) {
        chofer = choferVehiculoDaoFactory.updateChoferVehiculo(chlo_id, chofer);
        if (null == chofer)return new ResponseEntity<>(BODY + chlo_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chofer, HttpStatus.OK);
    }
}
