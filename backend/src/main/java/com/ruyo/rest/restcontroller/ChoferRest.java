package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.ChoferDao;
import com.ruyo.rest.entity.Chofer;
import com.ruyo.rest.exception.HandlingHttpStatus;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@UrlRestController
public class ChoferRest {

    private ChoferDao choferDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public ChoferRest(@Qualifier("choferDao")ChoferDao choferDaoFactory){
        this.choferDaoFactory = choferDaoFactory;
    }

    @GetMapping("/chofer")
    public ResponseEntity getChoferes(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Chofer> choferes = choferDaoFactory.getChoferes();

        if (choferes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(choferes);
        }

    }

    @GetMapping("/chofer/{chofer_id}")
    public ResponseEntity getChofer(@PathVariable(name = "chofer_id") long chofer_id) {
        Optional<Chofer> chofer = choferDaoFactory.getChofer(chofer_id);
        if (!chofer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + chofer_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(chofer);
        }
    }


    @PostMapping("/chofer")
    public ResponseEntity createChofer(Long empresa_id, Chofer chofer, HttpServletRequest request, HttpHeaders headers) {
        if(choferDaoFactory.add(empresa_id, chofer) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(chofer);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }
    }

    @DeleteMapping("/chofer/{chofer_id}")
    public ResponseEntity<Object> deleteChofer(@PathVariable Long chofer_id) {
        if (!choferDaoFactory.delete(chofer_id)) return new ResponseEntity<>
                (BODY + chofer_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chofer_id, HttpStatus.OK);
    }

    @PutMapping("/chofer/{id}")
    public ResponseEntity<Object> updateChofer(@PathVariable Long chofer_id, @RequestBody Chofer chofer) {
        chofer = choferDaoFactory.update(chofer_id, chofer);
        if (null == chofer)return new ResponseEntity<>(BODY + chofer_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chofer, HttpStatus.OK);
    }
}
