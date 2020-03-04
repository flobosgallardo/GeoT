package com.ruyo.geotclient.controller;

import com.ruyo.geotclient.controller.Utility.CustomSession;
import com.ruyo.geotclient.model.Cliente;
import com.ruyo.geotclient.security.EncryptPasswordHash;
import com.ruyo.geotclient.service.RestClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@SuppressWarnings("unchecked")
public class LoginController extends CustomSession {

    private RestClienteDao restClienteDaoService;

    @Autowired
    public LoginController(HttpSession session, RestClienteDao restClienteDaoService) {
        super(session);
        this.restClienteDaoService = restClienteDaoService;
    }

    @GetMapping("/login")
    private String showlogin(){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else{
            return "logged";
        }
    }

    @PostMapping("/login")
    private String loginprocess(@RequestParam(value = "username", defaultValue = "") String username,
                                @RequestParam(value = "password", defaultValue = "") String password, Model model) {
        String password_encrypted = new EncryptPasswordHash().encrypt(password.trim());
        Cliente _cliente = restClienteDaoService.userValidate(username.trim(), password_encrypted);
        if (_cliente == null) {
            return "redirect:login";
        } else {
            boolean isEnable = _cliente.isHabilitado();
            boolean isDelete = _cliente.isBorrado();
            String tipo = _cliente.getRol().getNombre();
            if (!username.equalsIgnoreCase(_cliente.getUsuario()) &
                    !password_encrypted.equalsIgnoreCase(_cliente.getContrasena())) {
                model.addAttribute("cliente_not_valid",true);
                return "login";
            }else {
                if (tipo.equalsIgnoreCase("normal") & isEnable & !isDelete) {
                    setSessionObject("cliente", _cliente);
                    return "redirect:panel_celular";
                } else if (tipo.equalsIgnoreCase("empresa") & isEnable & !isDelete) {
                    setCustomSessionObject(_cliente);
                    return "redirect:/";
                } else if (tipo.equalsIgnoreCase("administrador") & isEnable & !isDelete) {
                    setCustomSessionObject(_cliente);
                    return "redirect:/";
                } else if (tipo.equalsIgnoreCase("premium") & isEnable & !isDelete) {
                    setCustomSessionObject(_cliente);
                    return "redirect:/";
                } else {
                    return "login";
                }
            }

        }

    }

    @GetMapping("/logout")
    private String logoutprocess(){
        Cliente _cliente = (Cliente) getClienteSession();
        if(_cliente == null) {
            return "login";
        }else{
            getSession().invalidate();
            return "logout";
        }
    }

}
