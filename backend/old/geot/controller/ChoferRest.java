package old.geot.controller;

import com.ruyo.rest.geot.controller.utility.UrlRestController;
import com.ruyo.rest.geot.entity.Chofer;
import com.ruyo.rest.geot.exception.HandlingHttpStatus;
import com.ruyo.rest.geot.security.AuthBasic;
import com.ruyo.rest.geot.service.ChoferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@UrlRestController
public class ChoferRest extends AuthBasic {

    @Qualifier("choferService")
    private ChoferService choferServiceFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public ChoferRest (ChoferService choferServiceFactory){
        this.choferServiceFactory = choferServiceFactory;
    }

    @GetMapping("/chofer")
    public ResponseEntity getChoferes(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {
            List listClientes = choferServiceFactory.getAllChoferes();
            if (listClientes == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(listClientes);
            }
        }
    }

    @GetMapping("/chofer/{id}")
    public ResponseEntity<Object> getChofer(@PathVariable("id") Long id) {
        Chofer cliente = choferServiceFactory.findOne(id);
        if(cliente == null) return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping(value = "/chofer")
    public ResponseEntity createChofer(Chofer chofer, HttpServletRequest request) {
        if(choferServiceFactory.add(chofer))return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.IM_USED.toString());
        return handlingHttpStatus.ResponseFormatException(
                HttpStatus.IM_USED,
                HttpStatus.IM_USED.toString(),
                "El Chofer ya existe",
                request);
    }

    @DeleteMapping("/chofer/{id}")
    public ResponseEntity<Object> deleteChofer(@PathVariable Long id) {
        if (!choferServiceFactory.delete(choferServiceFactory.findOne(id)))return new ResponseEntity<>
                (BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/chofer/{id}")
    public ResponseEntity<Object> updateChofer(@PathVariable Long id, @RequestBody Chofer chofer) {
        chofer = choferServiceFactory.update(id, chofer);
        if (null == chofer)return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(chofer, HttpStatus.OK);
    }

}
