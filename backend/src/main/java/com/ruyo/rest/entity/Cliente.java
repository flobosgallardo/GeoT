package com.ruyo.rest.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "cliente")
@Table(name = "cliente", schema = "geot")
public class Cliente {
    private Long clienteId;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String contrasena;
    private boolean habilitado;
    private boolean borrado;
    private Rol rol;
    private List<Celular> celulares;
    private List<Empresa> empresas;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id", nullable = false)
    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Basic
    @Column(name = "nombre", nullable = true, length = 30)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellido", nullable = true, length = 30)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Basic
    @Column(name = "correo", nullable = true, length = 50)
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    @Basic
    @Column(name = "usuario", nullable = true, length = 30)
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "contrasena", nullable = true, length = 150)
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
        Cliente cliente = (Cliente) o;
        return (clienteId.equals(cliente.clienteId)) &&
                (habilitado == cliente.habilitado) &&
                (borrado == cliente.borrado) &&
                Objects.equals(nombre, cliente.nombre) &&
                Objects.equals(apellido, cliente.apellido) &&
                Objects.equals(correo, cliente.correo) &&
                Objects.equals(usuario, cliente.usuario) &&
                Objects.equals(contrasena, cliente.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId, nombre, apellido, correo, usuario, contrasena, habilitado, borrado);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SELECT)
    public List<Celular> getCelulares() {
        return celulares;
    }

    public void setCelulares(List<Celular> celulares) {
        this.celulares = celulares;
    }

}
