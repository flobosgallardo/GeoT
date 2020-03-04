package com.ruyo.geotclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehiculo {

    private Long vehiculoId;
    private String patente;
    private String marca;
    private String tipocombustible;
    private String modelo;
    private String tipodecambio;
    private boolean habilitado;
    private boolean borrado;
    private Gps gps;
    private List<DetalleVehiculo> detalleVehiculos;
    private Chofer chofer;


    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipocombustible() {
        return tipocombustible;
    }

    public void setTipocombustible(String tipocombustible) {
        this.tipocombustible = tipocombustible;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipodecambio() {
        return tipodecambio;
    }

    public void setTipodecambio(String tipodecambio) {
        this.tipodecambio = tipodecambio;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public List<DetalleVehiculo> getDetalleVehiculos() {
        return detalleVehiculos;
    }

    public void setDetalleVehiculos(List<DetalleVehiculo> detalleVehiculos) {
        this.detalleVehiculos = detalleVehiculos;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
    }
}
