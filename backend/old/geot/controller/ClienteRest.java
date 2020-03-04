package old.geot.controller;

import com.ruyo.rest.geot.controller.utility.UrlRestController;
import com.ruyo.rest.geot.dao.ClienteTestDao;
import com.ruyo.rest.geot.dao.EmpresaTestDao;
import com.ruyo.rest.geot.entity.Cliente;
import com.ruyo.rest.geot.entity.Empresa;
import com.ruyo.rest.geot.exception.HandlingHttpStatus;
import com.ruyo.rest.geot.security.AuthBasic;
import com.ruyo.rest.geot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@UrlRestController
public class ClienteRest extends AuthBasic {

    @Qualifier("clienteService")
    private ClienteService clienteServiceFactory;
    private ClienteTestDao clienteTestDao;
    private EmpresaTestDao empresaTestDao;

    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public ClienteRest (ClienteService clienteServiceFactory,
                        @Qualifier("clienteTestDao") ClienteTestDao clienteTestDao,
                        @Qualifier("empresaTestDao") EmpresaTestDao empresaTestDao){
        this.clienteServiceFactory = clienteServiceFactory;
        this.clienteTestDao = clienteTestDao;
        this.empresaTestDao = empresaTestDao;
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

        Collection<Cliente> allClientes = clienteTestDao.findAll();
        Collection<Empresa> all = empresaTestDao.findAll();
        for (Cliente allCliente : allClientes) {
            allCliente.setEmpresas(all);
        }

        if (allClientes == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(allClientes);
            }

    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Object> getCliente(@PathVariable("id") Long id) {
        Cliente cliente = clienteServiceFactory.findOne(id);
        if(cliente == null) return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping(value = "/cliente")
    public ResponseEntity createCliente(Cliente cliente, HttpServletRequest request) {
        if(clienteServiceFactory.add(cliente))return ResponseEntity.status(HttpStatus.CREATED).body(HttpStatus.IM_USED.toString());
        return handlingHttpStatus.ResponseFormatException(
                HttpStatus.IM_USED,
                HttpStatus.IM_USED.toString(),
                "El usuario ya existe",
                request);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        if (!clienteServiceFactory.delete(clienteServiceFactory.findOne(id)))return new ResponseEntity<>
                (BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente = clienteServiceFactory.update(id, cliente);
        if (null == cliente)return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
