/*package com.ruyo.rest.restcontroller;


import com.ruyo.rest.dao.ChoferDao;
import com.ruyo.rest.dao.ChoferVehiculoDao;
import com.ruyo.rest.dao.ClienteDao;
import com.ruyo.rest.dao.EmpresaDao;
import com.ruyo.rest.entity.Chofer;
import com.ruyo.rest.entity.ChoferVehiculo;
import com.ruyo.rest.entity.Cliente;
import com.ruyo.rest.entity.Empresa;
import com.ruyo.rest.restcontroller.utility.UrlRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@UrlRestController
public class TestUnit {

    private final ChoferDao choferDao;
    private final EmpresaDao empresaDao;
    private final ClienteDao clienteDao;
    private final ChoferVehiculoDao choferVehiculoDao;

    @Autowired
    public TestUnit(ChoferDao choferDao, EmpresaDao empresaDao, @Qualifier("clienteDao") ClienteDao clienteDao, ChoferVehiculoDao choferVehiculoDao) {
        this.choferDao = choferDao;
        this.empresaDao = empresaDao;
        this.clienteDao = clienteDao;
        this.choferVehiculoDao = choferVehiculoDao;
    }

    @GetMapping("/testcliente")
    private ResponseEntity<String> getCliente(){
        Cliente oneById = clienteDao.getOneById(1L);
        if(oneById != null) return ResponseEntity.status(HttpStatus.OK).body("1");
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/testaddempresa")
    private ResponseEntity addEmpresa(){
        Empresa empresa = new Empresa();
        empresa.setNombre("nombre");
        empresa.setRut("rut");
        empresa.setTelefono("telefono");
        empresa.setDireccion("direccion");
        Empresa add = empresaDao.add(1L, empresa);
        if(add != null) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/testaddchofer")
    private ResponseEntity addChofer(){
        Chofer chofer = new Chofer();
        chofer.setNombre("nombre");
        chofer.setRut("rut");
        chofer.setApellido("apellido");

        Chofer add = choferDao.add(1L, chofer);
        if(add != null) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @GetMapping("/addchofervehiculo")
    private ResponseEntity addChoferVehiculo(){
        ChoferVehiculo choferVehiculo = new ChoferVehiculo();
        choferVehiculo.setPatente("patente");
        ChoferVehiculo add = choferVehiculoDao.addChoferVehiculo(choferVehiculo);


        if(add != null) return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}
*/