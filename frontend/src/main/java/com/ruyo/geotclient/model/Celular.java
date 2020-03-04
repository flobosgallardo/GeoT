package com.ruyo.geotclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Celular {

    private Long celularId;
    private String modelo;
    private String marca;
    private String numtelefono;
    private boolean habilitado;
    private boolean borrado;
    private Cliente cliente;
    private Gps gps;

    public Long getCelularId() {
        return celularId;
    }

    public void setCelularId(Long celularId) {
        this.celularId = celularId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }
}
