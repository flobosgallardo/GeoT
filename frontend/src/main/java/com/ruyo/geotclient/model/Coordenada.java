package com.ruyo.geotclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordenada {

    private Long coord_id;
    private Double coordenadaX;
    private Double coordenadaY;
    private Double coordenadaZ;
    private Double distancia;
    private Gps gps;

    public Long getCoord_id() {
        return coord_id;
    }

    public void setCoord_id(Long coord_id) {
        this.coord_id = coord_id;
    }

    public Double getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(Double coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Double getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(Double coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Double getCoordenadaZ() {
        return coordenadaZ;
    }

    public void setCoordenadaZ(Double coordenadaZ) {
        this.coordenadaZ = coordenadaZ;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

}


