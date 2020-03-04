package com.ruyo.rest.restcontroller;


import com.ruyo.rest.dao.EmpresaDao;
import com.ruyo.rest.entity.Empresa;
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
public class EmpresaRest {

    private EmpresaDao empresaDaoFactory;
    private String BODY = "no se ha encontrado ningun empleado con este ID: ";
    private HandlingHttpStatus handlingHttpStatus = new HandlingHttpStatus();

    @Autowired
    public EmpresaRest(@Qualifier("empresaDao") EmpresaDao empresaDaoFactory) {
        this.empresaDaoFactory = empresaDaoFactory;
    }

    @GetMapping("/empresa")
    public ResponseEntity getEmpresas(@RequestHeader HttpHeaders headers, HttpServletRequest request) {
        /*if (!isUserValid(headers)) {
            return handlingHttpStatus.ResponseFormatException(
                    HttpStatus.UNAUTHORIZED,
                    HttpStatus.UNAUTHORIZED.toString(),
                    "Solicitud Prohibida",
                    request);
        } else {*/

        /*String allClientes = clienteServiceFactory.getAllClientes();*/

        Collection<Empresa> empresas = empresaDaoFactory.getEmpresas();

        if (empresas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(empresas);
        }

    }

    @GetMapping("/empresa/{empresa_id}")
    public ResponseEntity getEmpresa(@PathVariable(name = "empresa_id") long cliente_id) {
        Optional<Empresa> empresa = empresaDaoFactory.getEmpresa(cliente_id);
        if (!empresa.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BODY + cliente_id);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(empresa);
        }
    }

    @PostMapping(value = "/empresa")
    public ResponseEntity createEmpresa(Empresa empresa, Long cliente_id, HttpServletRequest request) {
        Empresa add = empresaDaoFactory.add(cliente_id, empresa);
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

    @DeleteMapping("/empresa/{empresa_id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable Long empresa_id) {
        if (!empresaDaoFactory.delete(empresa_id)) return new ResponseEntity<>
                (BODY + empresa_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empresa_id, HttpStatus.OK);
    }

    @PutMapping("/empresa/{id}")
    public ResponseEntity<Object> updateEmpresa(@PathVariable Long empresa_id, @RequestBody Empresa empresa) {
        Empresa update = empresaDaoFactory.update(empresa_id, empresa);
        if (null == update)return new ResponseEntity<>(BODY + empresa_id, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

}
