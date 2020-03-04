package com.ruyo.geotclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalleVehiculo {

    private long detallevehiculoId;
    private Double combUsadoTotal;
    private Double combUsuadoRecorrido;
    private Date fecha;
    private boolean habilitado;
    private boolean borrado;
    private Vehiculo vehiculoByVehiculoId;

    public long getDetallevehiculoId() {
        return detallevehiculoId;
    }

    public void setDetallevehiculoId(long detallevehiculoId) {
        this.detallevehiculoId = detallevehiculoId;
    }

    public Double getCombUsadoTotal() {
        return combUsadoTotal;
    }

    public void setCombUsadoTotal(Double combUsadoTotal) {
        this.combUsadoTotal = combUsadoTotal;
    }

    public Double getCombUsuadoRecorrido() {
        return combUsuadoRecorrido;
    }

    public void setCombUsuadoRecorrido(Double combUsuadoRecorrido) {
        this.combUsuadoRecorrido = combUsuadoRecorrido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public Vehiculo getVehiculo() {
        return vehiculoByVehiculoId;
    }

    public void setVehiculo(Vehiculo vehiculoByVehiculoId) {
        this.vehiculoByVehiculoId = vehiculoByVehiculoId;
    }
}
