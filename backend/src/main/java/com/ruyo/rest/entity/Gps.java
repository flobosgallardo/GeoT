package com.ruyo.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity(name = "gps")
@Table(name = "gps", schema = "geot")
public class Gps {
    private Long gpsId;
    private String pais;
    private boolean habilitado;
    private boolean borrado;
    private Celular celular;
    private Vehiculo vehiculo;
    private List<Coordenada> coordenadas;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gps_id", nullable = false)
    public Long getGpsId() {
        return gpsId;
    }

    public void setGpsId(Long gpsId) {
        this.gpsId = gpsId;
    }

    @Basic
    @Column(name = "pais", nullable = true, length = 30)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
    @JoinColumn(name = "celular_id", referencedColumnName = "celular_id")
    @JsonProperty(access = WRITE_ONLY)
    public Celular getCelular() {
        return celular;
    }

    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    @OneToOne
    @JoinColumn(name = "vehiculo_id", referencedColumnName = "vehiculo_id")
    @JsonProperty(access = WRITE_ONLY)
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    @OneToMany(mappedBy = "gps", fetch = FetchType.EAGER)
    public List<Coordenada> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gps gps = (Gps) o;
        return gpsId.equals(gps.gpsId) &&
                habilitado == gps.habilitado &&
                borrado == gps.borrado &&
                Objects.equals(pais, gps.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gpsId, pais, habilitado, borrado);
    }



}
