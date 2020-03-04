package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.Nombres;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.model.Empresa;
import com.ruyo.geotclient.service.RestEmpresaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class EmpresaController{

    private final RestEmpresaDao service;
    private final HttpSession session;

    @Autowired
    public EmpresaController(RestEmpresaDao service, HttpSession session){
        this.service = service;
        this.session = session;
    }

    @GetMapping("/empresa")
    public String getClientes(Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("empresas", service.getEmpresas());
            return "empresas";
        }
    }

    @GetMapping("/empresa/{id}")
    public String getByOne(@PathVariable String id, Model model){
        Cliente _cliente = (Cliente) session.getAttribute("cliente");
        if(_cliente == null) {
            return "login";
        }else {
            Empresa empresa = service.getEmpresa(id);
            model.addAttribute("empresa", empresa);
            model.addAttribute("choferes", empresa.getChoferes());
            model.addAttribute("nombreCliente", Nombres.getUtilidad());
            return "showempresa";
        }
    }
}
