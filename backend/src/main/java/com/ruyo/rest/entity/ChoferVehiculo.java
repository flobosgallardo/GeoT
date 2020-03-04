package com.ruyo.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity(name = "chofer_vehiculo")
@Table(name = "chofer_vehiculo",schema = "geot")
public class ChoferVehiculo {
    private Long chlo_id;
    private String patente;
    private boolean habilitado;
    private boolean borrado;
    private Chofer choferByChoferId;
    private Vehiculo vehiculoByVehiculoId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chlo_id", nullable = false)
    public Long getChlo_id() {
        return chlo_id;
    }

    public void setChlo_id(Long chlo_id) {
        this.chlo_id = chlo_id;
    }

    @Basic
    @Column(name = "patente", nullable = true, precision = 0)
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
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

    @OneToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "vehiculo_id")
    @JsonProperty(access = WRITE_ONLY)
    public Vehiculo getVehiculo() {
        return vehiculoByVehiculoId;
    }

    public void setVehiculo(Vehiculo vehiculoByVehiculoId) {
        this.vehiculoByVehiculoId = vehiculoByVehiculoId;
    }

    @OneToOne
    @JoinColumn(name = "chofer_id", referencedColumnName = "chofer_id")
    @JsonProperty(access = WRITE_ONLY)
    public Chofer getChoferByChoferId() {
        return choferByChoferId;
    }

    public void setChoferByChoferId(Chofer choferByChoferId) {
        this.choferByChoferId = choferByChoferId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoferVehiculo that = (ChoferVehiculo) o;
        return chlo_id.equals(that.chlo_id) &&
                habilitado == that.habilitado &&
                borrado == that.borrado &&
                Objects.equals(patente, that.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chlo_id, patente, habilitado, borrado);
    }


}
