package com.ruyo.rest.restcontroller;

import com.ruyo.rest.dao.RolDao;
import com.ruyo.rest.entity.Cliente;
import com.ruyo.rest.entity.Rol;
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
public class RolRest {

    private RolDao rolDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public RolRest(@Qualifier("rolDao") RolDao rolDaoFactory) {
        this.rolDaoFactory = rolDaoFactory;
    }


    @GetMapping("/rol")
    public ResponseEntity getRoles(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Rol> roles = rolDaoFactory.getRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(roles);
        }

    }

    @GetMapping("/rol/{rol_id}")

    public ResponseEntity getRol(@PathVariable(name = "rol_id") long rol_id) {
        Optional<Rol> rol = rolDaoFactory.getRol(rol_id);
        if (!rol.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + rol_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(rol);
        }
    }


    @PostMapping(value = "/rol")
    public ResponseEntity createRol(Rol rol, HttpServletRequest request) {
        if(rolDaoFactory.add(rol) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(rol);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }
    }

    @PutMapping("/rol/{rol_id}")
    public ResponseEntity<Object> updateRol(@PathVariable Long rol_id, @RequestBody Rol rol) {
        rol = rolDaoFactory.update(rol_id, rol);
        if (null == rol)return new ResponseEntity<>(BODY + rol_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }
}
