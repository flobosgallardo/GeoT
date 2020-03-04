package old.geot.security;

import com.ruyo.rest.geot.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;

import java.util.List;

public class AuthBasic {

    private final int TYPE = 0, CREDENTIALS = 1,USER = 0 , PASS = 1;
    private final String AUTHORIZATION = "Authorization";

    @Autowired
    private ClienteService service;

    public boolean isUserValid(HttpHeaders headers){
        List<String> authorization = headers.get(AUTHORIZATION);
        if(authorization != null){
            if(authorization.size() != 0){
                for(String aux : authorization){
                    String[] val = aux.split(" ");
                    if(val.length != 2){
                        //System.err.println("No tiene la sintaxis de un HTTP Authorization");
                        return false;
                    }else{
                        String tipo = val[TYPE].trim();
                        String valor = val[CREDENTIALS].trim();
                        /*System.out.println("Type: " + tipo);
                        System.out.println("Credentians: " + valor );*/
                        try{
                            String usuario_pass = new String(Base64Utils.decode(valor.getBytes()));
                            //System.out.println("usuario pass:" + usuario_pass);
                            String[] crendenciales = usuario_pass.split(":");
                            if(crendenciales.length != 2){
                                //System.err.println("Error no tiene el formato\tusuario:pass");
                                return false;
                            }else{
                                String user = crendenciales[USER];
                                String pass = crendenciales[PASS];

                                //System.out.println("El usuario (decodificado) es:" + user);
                                //System.out.println("La password (decodificada) es:" + pass);

                                if(service.isUserValid(user,pass)){
                                    //System.out.println("Usuario valido!");
                                    return true;
                                }else{
                                    //System.out.println("Usuario en silla de ruedas");
                                    return false;
                                }

                            }
                        }catch(IllegalArgumentException ex){
                           // System.err.println("El valor de las credenciales no esta en base64");
                            return false;

                        }
                    }
                }
            }else{
               // System.err.println("No tiene las cabezeras de auth");
                return false;
            }
        }else{
            //System.err.println("No tiene las cabezeras de auth");
            return false;
        }
        return false;

    }
}
