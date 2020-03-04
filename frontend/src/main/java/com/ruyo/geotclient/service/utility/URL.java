package com.ruyo.geotclient.service.utility;

public class URL {

    //private static final String URL =  "http://ruyo.softether.net:8080/zgeot/api";
    private static final String URL =  "http://192.168.0.11:8080/zgeot/api";
    //private static final String URL = "http://localhost:8080/zgeot/api";
    //private static final String URL = "http://localhost:8080/api";

    private static final String CLIENTE = "/cliente";
    private static final String CLIENTE_BYID = "/cliente/";
    private static final String CLIENTEBYPARAM = "/validatecliente";
    private static final String CLIENTEBYUSUARIO = "/clientebyuser";
    private static final String CLIENTEBYEMAIL = "/clientebyemail";

    private static final String CELULAR_ALL = "/celular";
    private static final String CELULAR_BYID = "/celular/";

    private static final String EMPRESA_ALL = "/empresa";
    private static final String EMPRESA_BYID = "/empresa/";

    private static final String CHOFER_ALL = "/chofer";
    private static final String CHOFER_BYID = "/chofer/";

    private static final String VEHICULO_ALL = "/vehiculo";
    private static final String VEHICULO_BYID = "/vehiculo/";

    private static final String GPS_ALL = "/gps";
    private static final String GPS_BYID = "/gps/";

    private static final String ROL_ALL = "/rol";
    private static final String ROL_BYID = "/rol/";

    private static final String DETALLEVEHICULO_ALL = "/detallevehiculo";
    private static final String DETALLEVEHICULO_BYID = "/detallevehiculo/";

    public static String getCLIENTE() {
        return URL + CLIENTE;
    }

    public static String getCLIENTE_BYID(String id) {
        return URL + CLIENTE_BYID + id;
    }

    public static String getCLIENTEBYPARAM(String usuario,String contrasena) {
        String aux = "?usuario=" + usuario + "&contrasena="+contrasena;
        return URL + CLIENTEBYPARAM + aux;
    }

    public static String getCLIENTEBYUSUARIO(String usuario) {
        String aux = "?usuario=" + usuario;
        return URL + CLIENTEBYUSUARIO + aux;
    }

    public static String getCLIENTEBYEMAIL(String correo) {
        String aux = "?correo=" + correo;
        return URL + CLIENTEBYEMAIL + aux;
    }

    public static String getCELULAR() {
        return URL + CELULAR_ALL;
    }

    public static String getCELULAR_BYID(String id) {
        return URL + CELULAR_BYID + id;
    }

    public static String getEMPRESA_ALL() {
        return URL + EMPRESA_ALL;
    }

    public static String getEMPRESA_BYID() {
        return URL + EMPRESA_BYID;
    }

    public static String getCHOFER_ALL() {
        return CHOFER_ALL;
    }

    public static String getCHOFER_BYID() {
        return URL + CHOFER_BYID;
    }

    public static String getVEHICULO_ALL() {
        return URL + VEHICULO_ALL;
    }

    public static String getVEHICULO_BYID () {
        return URL + VEHICULO_BYID;
    }

    public static String getGPS_ALL() {
        return URL + GPS_ALL;
    }

    public static String getGPS_BYID(String id) {
        return URL + GPS_BYID + id;
    }

    public static String getROL_ALL() {
        return URL + ROL_ALL;
    }

    public static String getROL_BYID() {
        return URL + ROL_BYID;
    }

    public static String getDETALLEVEHICULO_ALL() {
        return URL + DETALLEVEHICULO_ALL;
    }

    public static String getDETALLEVEHICULO_BYID() {
        return URL + DETALLEVEHICULO_BYID;
    }
}
