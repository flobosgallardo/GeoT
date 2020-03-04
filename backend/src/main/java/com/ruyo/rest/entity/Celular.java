package com.ruyo.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity(name = "celular")
@Table(name = "celular", schema = "geot")
public class Celular {
    private Long celularId;
    private String modelo;
    private String marca;
    private String numtelefono;
    private boolean habilitado;
    private boolean borrado;
    private Cliente cliente;
    private Gps gps;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "celular_id", nullable = false)
    public Long getCelularId() {
        return celularId;
    }

    public void setCelularId(Long clientecelularId) {
        this.celularId = clientecelularId;
    }

    @Basic
    @Column(name = "modelo", nullable = true, length = 50)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "marca", nullable = true, length = 50)
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Basic
    @Column(name = "numtelefono", nullable = true, length = 25)
    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Celular that = (Celular) o;
        return celularId.equals(that.celularId) &&
                habilitado == that.habilitado &&
                borrado == that.borrado &&
                Objects.equals(modelo, that.modelo) &&
                Objects.equals(marca, that.marca) &&
                Objects.equals(numtelefono, that.numtelefono);
    }

    @Override
    public int hashCode() {

        return Objects.hash(celularId, modelo, marca, numtelefono, habilitado, borrado);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id")
    @JsonProperty(access = WRITE_ONLY)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @OneToOne(mappedBy = "celular", fetch = FetchType.EAGER)
    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }


}
