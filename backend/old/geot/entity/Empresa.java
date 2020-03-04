package old.geot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity(name = "empresa")
@Table(name = "empresa", schema = "geot",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "nombre", "rut" }
                )
        })
public class Empresa implements Serializable {

    private Long empresaId;
    @JsonIgnore
    private Cliente cliente;
    private String nombre;
    private String direccion;
    private String razonsocial;
    private String rut;
    private String telefono;
    private boolean habilitado;
    private boolean borrado;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id", nullable = false, unique = true)
    public Long getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "cliente_id", nullable = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    @Column(name = "direccion", nullable = true, length = 150)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Basic
    @Column(name = "razonsocial", nullable = true, length = 50)
    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    @Basic
    @Column(name = "rut", nullable = true, length = 20)
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Basic
    @Column(name = "telefono", nullable = true, length = 20)
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        Empresa empresa = (Empresa) o;
        return empresaId.equals(empresa.empresaId) &&
                (habilitado == empresa.habilitado) &&
                (borrado == empresa.borrado) &&
                Objects.equals(nombre, empresa.nombre) &&
                Objects.equals(direccion, empresa.direccion) &&
                Objects.equals(razonsocial, empresa.razonsocial) &&
                Objects.equals(rut, empresa.rut) &&
                Objects.equals(telefono, empresa.telefono);
    }

    @Override
    public int hashCode() {

        return Objects.hash(empresaId, nombre, direccion, razonsocial, rut, telefono, habilitado, borrado);
    }

}
