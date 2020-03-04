package old.geot.controller;

import com.ruyo.rest.geot.controller.utility.UrlRestController;
import com.ruyo.rest.geot.entity.Empresa;
import com.ruyo.rest.geot.exception.HandlingHttpStatus;
import com.ruyo.rest.geot.security.AuthBasic;
import com.ruyo.rest.geot.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@UrlRestController
public class EmpresaRest extends AuthBasic {

    @Qualifier("empresaService")
    private EmpresaService empresaServiceFactory;
    private final String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public EmpresaRest (EmpresaService empresaServiceFactory){
        this.empresaServiceFactory = empresaServiceFactory;
    }

    @GetMapping("/empresa")
    public ResponseEntity getEmpresas(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {
            List<Empresa> listEmpresas = empresaServiceFactory.getAllEmpresa();
            if (listEmpresas == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(listEmpresas);
            }
        }
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Object> getEmpresa(@PathVariable("id") Long id) {
        Empresa empresa = empresaServiceFactory.findOne(id);
        if (empresa == null) return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @GetMapping("/empresa/{id}/cliente")
    public ResponseEntity<Object> getEmpresaCliente (@PathVariable("id") Long id) {
        Empresa empresa = empresaServiceFactory.findOne(id);
        if (empresa == null) return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    @PostMapping(value = "/empresa")
    public ResponseEntity createEmpresa(Empresa empresa, HttpServletRequest request) {
        if(empresaServiceFactory.add(empresa))  return ResponseEntity.status(HttpStatus.CREATED).build();
        return handlingHttpStatus.ResponseFormatException(
                HttpStatus.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.toString(),
                "Solicitud Prohibida",
                request);
    }

    @DeleteMapping("/empresa/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable Long id) {
        if (!empresaServiceFactory.delete(empresaServiceFactory.findOne(id)))return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/empresa/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id, @RequestBody Empresa empresa) {
        empresa = empresaServiceFactory.update(id, empresa);
        if (null == empresa)return new ResponseEntity<>(BODY + id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
}
