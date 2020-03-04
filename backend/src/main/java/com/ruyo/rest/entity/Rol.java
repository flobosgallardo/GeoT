package com.ruyo.rest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Entity(name = "rol")
@Table(name = "rol", schema = "geot")
public class Rol {
    private Long rolId;
    private String nombre;
    private String descripcion;
    private List<Cliente> clientes;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id", nullable = false)
    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion", nullable = true, length = 150)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return rolId.equals(rol.rolId) &&
                Objects.equals(nombre, rol.nombre) &&
                Objects.equals(descripcion, rol.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolId, nombre, descripcion);
    }

    @OneToMany(mappedBy = "rol", fetch = FetchType.EAGER)
    @JsonProperty(access = WRITE_ONLY)
    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
