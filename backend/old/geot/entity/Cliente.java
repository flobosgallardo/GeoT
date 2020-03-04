package old.geot.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;


@Entity(name = "cliente")
@Table(name = "cliente", schema = "geot",
        uniqueConstraints = {
        @UniqueConstraint(
                columnNames = { "usuario" }
                )
})
public class Cliente implements Serializable {
    private Long clienteId;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    private boolean habilitado;
    private boolean borrado;
    private Collection<Empresa> empresas = new ArrayList<>();


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cliente_id", nullable = false, unique = true)
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

    @OneToMany(mappedBy = "cliente")
    public Collection<Empresa> getEmpresas() {
        return this.empresas;
    }

    public void setEmpresas(Collection<Empresa> empresas) {
        this.empresas = empresas;
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
                Objects.equals(usuario, cliente.usuario) &&
                Objects.equals(contrasena, cliente.contrasena);
    }

    @Override
    public int hashCode() {

        return Objects.hash(clienteId, nombre, apellido, usuario, contrasena, habilitado, borrado);
    }
}

/*
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name="getAllClientes",
                procedureName="getAllClientes",
                resultClasses = Cliente.class
        ),
        @NamedStoredProcedureQuery(
                name="validateUsuario",
                procedureName="validateUsuario",
                resultClasses = Cliente.class,
                parameters={
                        @StoredProcedureParameter(name = "usuario_", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "contrasena_", mode = ParameterMode.IN, type = String.class)}
        )
})
 */