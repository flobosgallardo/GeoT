package com.ruyo.rest.restcontroller;


import com.ruyo.rest.dao.ClienteDao;
import com.ruyo.rest.entity.Cliente;
import com.ruyo.rest.exception.HandlingHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ruyo.rest.restcontroller.utility.UrlRestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@UrlRestController
public class ClienteRest  {

    private ClienteDao clienteDaoFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public ClienteRest(@Qualifier("clienteDao") ClienteDao clienteDaoFactory) {
        this.clienteDaoFactory = clienteDaoFactory;
    }


    @GetMapping("/cliente")
    public ResponseEntity getClientes(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Cliente> clientes = clienteDaoFactory.getClientes();

        if (clientes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(clientes);
        }

    }

    @GetMapping("/cliente/{cliente_id}")
    public ResponseEntity getClientes(@PathVariable(name = "cliente_id") long cliente_id) {
        Optional<Cliente> cliente = clienteDaoFactory.getCliente(cliente_id);
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + cliente_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
    }


    @PostMapping("/cliente")
    public ResponseEntity createCliente(@RequestBody Cliente cliente, HttpServletRequest request) {
        if(clienteDaoFactory.add(cliente) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }
    }

    @GetMapping("/validatecliente")
    public ResponseEntity validateclient(@RequestParam String usuario,
                                         @RequestParam String contrasena,
                                         HttpServletRequest request) {
        /*if(*/
        Cliente cliente = clienteDaoFactory.userValidate(usuario, contrasena);/*!= null) {*/
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        /*}else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }*/
    }


    @GetMapping("/clientebyuser")
    public ResponseEntity getClientByusuario(@RequestParam String usuario, HttpServletRequest request) {
        /*if(*/
        Cliente cliente = clienteDaoFactory.getClienteByUsuario(usuario);/*!= null) {*/
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        /*}else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }*/
    }

    @GetMapping("/clientebyemail")
    public ResponseEntity validateclient(@RequestParam String correo,
                                         HttpServletRequest request) {
        /*if(*/
        Cliente cliente = clienteDaoFactory.getClienteByCorreo(correo);/*!= null) {*/
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        /*}else {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.IM_USED,
                    HttpStatus.IM_USED.toString(),
                    "El usuario ya existe",
                    request);
        }*/
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long cliente_id) {
        if (!clienteDaoFactory.delete(cliente_id)) return new ResponseEntity<>
                (BODY + cliente_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cliente_id, HttpStatus.OK);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long cliente_id, @RequestBody Cliente cliente) {
        cliente = clienteDaoFactory.update(cliente_id, cliente);
        if (null == cliente)return new ResponseEntity<>(BODY + cliente_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
