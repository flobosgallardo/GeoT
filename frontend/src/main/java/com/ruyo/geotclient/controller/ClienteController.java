package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.Nombres;
import com.ruyo.geotclient.controller.Utility.CustomSession;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.model.Rol;
import com.ruyo.geotclient.security.EncryptPasswordHash;
import com.ruyo.geotclient.service.RestClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ClienteController extends CustomSession {

    private final RestClienteDao service;

    @Autowired
    public ClienteController(HttpSession session, RestClienteDao service) {
        super(session);
        this.service = service;
    }

    @GetMapping("/cliente")
    public String getClientes(Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else{
            model.addAttribute("clientes", service.getClientes());
            return "clientes";
        }

    }

    @GetMapping("/cliente/{id}")
    public String getByOne(@PathVariable String id, Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else {
            Cliente cliente = service.getCliente(id);
            Nombres.setUtilidad(cliente.getNombre() + " " + cliente.getApellido());
            model.addAttribute("rol", cliente.getRol());
            model.addAttribute("empresas", cliente.getEmpresas());
            model.addAttribute("celulares", cliente.getCelulares());
            model.addAttribute("cliente", cliente);
            return "showcliente";
        }
    }



    @PostMapping("/process_account")
    public String createaccountprocess(@ModelAttribute(value = "cliente") Cliente cliente, Model model) {
        String usuario = cliente.getUsuario().trim();

        Cliente _cliente = service.getClienteByUsuario(usuario);

        if(_cliente == null) {
            Rol rol = new Rol();
            rol.setRolId(1L);
            cliente.setRol(rol);
            if(null != service.add(cliente)){
                return "login";
            }else {
                model.addAttribute("cliente_exist", true);
                return "createaccount";
            }

        }else {
            if (_cliente.getUsuario().equalsIgnoreCase(usuario)) {
                model.addAttribute("usuario_exist", true);
            }
            return "createaccount";

        }


    }

    @GetMapping("/createaccount")
    private String showcreateaccount(Model model){
        model.addAttribute("cliente", new Cliente());
        return "createaccount";
    }

    @GetMapping("/panel_account")
    private String showpanelaccount(Model model){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else {
            model.addAttribute("cliente", _cliente);
            return "panel_account";
        }
    }
}


