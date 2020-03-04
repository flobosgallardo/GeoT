package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.Nombres;
import com.ruyo.geotclient.model.Chofer;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.service.RestChoferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class ChoferController {

    private RestChoferDao service;
    private final HttpSession session;

    @Autowired
    public ChoferController(RestChoferDao service, HttpSession session){
        this.service = service;
        this.session = session;
    }

    @GetMapping("/chofer")
    public String getChoferes(Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("choferes", service.getChoferes());
            return "choferes";
        }
    }

    @GetMapping("/chofer/{id}")
    public String getByOne(@PathVariable String id, Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            Chofer chofer = service.getChofer(id);
            model.addAttribute("chofer", chofer);
            model.addAttribute("vehiculos", chofer.getVehiculo());
            model.addAttribute("nombreCliente", Nombres.getUtilidad());
            return "showchofer";
        }
    }
}
