package com.ruyo.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity(name = "vehiculo")
@Table(name = "vehiculo", schema = "geot")
public class Vehiculo {
    private Long vehiculoId;
    private float rendimiento;
    private String marca;
    private String tipoCombustible;
    private String modelo;
    private boolean habilitado;
    private boolean borrado;
    private ChoferVehiculo choferVehiculo;
    private Gps gps;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehiculo_id", nullable = false)
    public Long getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Long vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    @Basic
    @Column(name = "rendimiento", nullable = false)
    public float getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(float rendimiento) {
        this.rendimiento = rendimiento;
    }

    @Basic
    @Column(name = "marca", nullable = false)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "tipocombustible", nullable = false)
    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    @Basic
    @Column(name = "modelo", nullable = false)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "habilitado", nullable = false)
    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Basic
    @Column(name = "borrado", nullable = false)
    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    @OneToOne(mappedBy = "vehiculo")
    @JsonProperty(access = WRITE_ONLY)
    public ChoferVehiculo getChoferVehiculo() {
        return choferVehiculo;
    }

    public void setChoferVehiculo(ChoferVehiculo choferVehiculo) {
        this.choferVehiculo = choferVehiculo;
    }

    @OneToOne(mappedBy = "vehiculo", fetch = FetchType.EAGER)

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return vehiculoId.equals(vehiculo.vehiculoId) &&
                habilitado == vehiculo.habilitado &&
                borrado == vehiculo.borrado;
    }

    @Override
    public int hashCode() {

        return Objects.hash(vehiculoId, habilitado, borrado);
    }

}
