package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.CustomSession;
import com.ruyo.geotclient.model.Celular;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.service.RestCelularDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CelularController extends CustomSession {

    private final RestCelularDao service;

    @Autowired
    public CelularController(RestCelularDao service, HttpSession session){
        super(session);
        this.service = service;
    }

    @GetMapping("/celular")
    public String getCelulares(Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("celulares", service.getCelulares());
            return "celulares";
        }
    }

    @GetMapping("/celular/{id}")
    public String getByOne(@PathVariable String id, Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else{
            Celular celular = service.getCelular(id);
            model.addAttribute("celular", celular);
            model.addAttribute("gps", celular.getGps());
            model.addAttribute("nombreCliente", _cliente.getUsuario());
            return "showcelular";
        }
    }

    @PostMapping("/process_celular")
    public String process_celular(@ModelAttribute(value = "celular") Celular celular, Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("nombre", _cliente.getNombre());
            celular.setCliente(_cliente);
            service.add(celular);
            return "redirect:panel_celular";
        }
    }

    @GetMapping("/panel_celular")
    public String showcelular_cliente(Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else{
            model.addAttribute("nombreCliente", _cliente.getNombre());
            model.addAttribute("celular", new Celular());
            model.addAttribute("celulares", _cliente.getCelulares());
            return "panel_celular";
        }
    }


}
