package com.ruyo.rest.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity(name = "coordenada")
@Table(name = "coordenada", schema = "geot")
public class Coordenada {

    private Long coord_id;
    private double coordenadaX;
    private double coordenadaY;
    private double distancia;
    private Gps gps;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coord_id", nullable = false)
    public Long getCoord_id() {
        return coord_id;
    }

    public void setCoord_id(Long coord_id) {
        this.coord_id = coord_id;
    }

    @Basic
    @Column(name = "coordenadaX", nullable = true, precision = 6)
    public double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    @Basic
    @Column(name = "coordenadaY", nullable = true, precision = 6)
    public double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    @Basic
    @Column(name = "distancia", nullable = true, precision = 6)
    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }


    @ManyToOne
    @JoinColumn(name = "gps_id", referencedColumnName = "gps_id")
    @JsonProperty(access = WRITE_ONLY)
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
        Coordenada that = (Coordenada) o;
        return Double.compare(that.coordenadaX, coordenadaX) == 0 &&
                Double.compare(that.coordenadaY, coordenadaY) == 0 &&
                Double.compare(that.distancia, distancia) == 0 &&
                Objects.equals(coord_id, that.coord_id) &&
                Objects.equals(gps, that.gps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coord_id, coordenadaX, coordenadaY, distancia, gps);
    }
}


