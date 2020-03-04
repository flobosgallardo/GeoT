package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.Nombres;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.model.Vehiculo;
import com.ruyo.geotclient.service.RestVehiculoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class VehiculoController {

    private final RestVehiculoDao service;
    private final HttpSession session;

    @Autowired
    public VehiculoController(RestVehiculoDao service, HttpSession session){
        this.service = service;
        this.session = session;
    }

    @GetMapping("/vehiculo")
    public String getVehiculos(Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("vehiculos", service.getVehiculos());
            return "vehiculos";
        }
    }

    @GetMapping("/vehiculo/{id}")
    public String getVehiculo(@PathVariable String id, Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            Vehiculo vehiculo = service.getVehiculo(id);
            model.addAttribute("vehiculo", vehiculo);
            model.addAttribute("gps", vehiculo.getGps());
            model.addAttribute("nombreCliente", Nombres.getUtilidad());
            return "showvehiculo";
        }
    }
}
