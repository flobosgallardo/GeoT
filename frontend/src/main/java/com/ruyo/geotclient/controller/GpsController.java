package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.CustomSession;
import com.ruyo.geotclient.controller.Utility.Nombres;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.model.Coordenada;
import com.ruyo.geotclient.model.Gps;
import com.ruyo.geotclient.service.RestGpsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class GpsController extends CustomSession {

    private final RestGpsDao service;

    @Autowired
    public GpsController(RestGpsDao service, HttpSession session){
        super(session);
        this.service = service;
    }

    @GetMapping("/gps")
    public String getGpsList(Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("vehiculos", service.getGpsList());
            return "gpslist";
        }
    }

    @GetMapping("celular/gps/{id}")
    public String getGps(@PathVariable String id, Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else {

            Gps gps = service.getGps(id);
            /*model.addAttribute("gps", gps);
            model.addAttribute("coordenadas", gps.getCoordenadas());
            model.addAttribute("nombreCliente", Nombres.getUtilidad());*/
            return "map";
        }
    }
}
