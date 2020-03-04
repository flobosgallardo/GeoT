package old.geot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "chofer")
@Table(name = "chofer", schema = "geot",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = { "rut" }
                )
        })
public class Chofer {
    private Long choferId;
    private String nombre;
    private String apellido;
    private String rut;
    private boolean habilitado;
    private boolean borrado;
    private Empresa empresaByEmpresaId;
    /*private Collection<Vehiculo> vehiculosByChoferId = new HashSet<>(0);*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chofer_id", nullable = false)
    public Long getChoferId() {
        return choferId;
    }

    public void setChoferId(Long choferId) {
        this.choferId = choferId;
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
    @Column(name = "rut", nullable = true, length = 30)
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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
        Chofer chofer = (Chofer) o;
        return choferId == chofer.choferId &&
                habilitado == chofer.habilitado &&
                borrado == chofer.borrado &&
                Objects.equals(nombre, chofer.nombre) &&
                Objects.equals(apellido, chofer.apellido) &&
                Objects.equals(rut, chofer.rut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(choferId, nombre, apellido, rut, habilitado, borrado);
    }

    @ManyToOne
    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    public Empresa getEmpresaByEmpresaId() {
        return empresaByEmpresaId;
    }

    public void setEmpresaByEmpresaId(Empresa empresaByEmpresaId) {
        this.empresaByEmpresaId = empresaByEmpresaId;
    }
/*
    @OneToOne(mappedBy = "choferByChoferId")
    public Collection<Vehiculo> getVehiculosByChoferId() {
        return vehiculosByChoferId;
    }

    public void setVehiculosByChoferId(Collection<Vehiculo> vehiculosByChoferId) {
        this.vehiculosByChoferId = vehiculosByChoferId;
    }*/
}
