package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.service.RestRolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RolController {

    private final RestRolDao service;
    private final HttpSession session;

    @Autowired
    public RolController(RestRolDao service, HttpSession session){
        this.service = service;
        this.session = session;
    }

    @GetMapping("/rol")
    public String getRoles(Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("roles", service.getRoles());
            return "roles";
        }
    }

}
