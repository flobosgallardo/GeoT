package old.geot.dao.factory;

import com.ruyo.rest.geot.dao.EmpresaDao;
import com.ruyo.rest.geot.dao.utility.DaoFactoryUtility;
import com.ruyo.rest.geot.entity.Empresa;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("empresaDao")
public class EmpresaDaoFactory extends DaoFactoryUtility implements EmpresaDao {

    @Override
    public boolean add(Empresa empresa) {
        String rut = findOneByRut(empresa.getRut());
        if(rut == null) {
            empresa.setEmpresaId(0L);
            empresa.setHabilitado(true);
            empresa.setBorrado(false);
            return addEntity(empresa);
        }else if(!rut.equalsIgnoreCase(empresa.getRut())){
            empresa.setEmpresaId(0L);
            empresa.setHabilitado(true);
            empresa.setBorrado(false);
            return addEntity(empresa);
        }
        return false;
    }

    @Override
    public Empresa findOne(Long id) {
        try {
            Session session = openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Empresa> query = cb.createQuery(Empresa.class);
            Root<Empresa> empresa = query.from(Empresa.class);
            Predicate predicate = cb.and(
                    cb.equal(empresa.get("id"), id));
            query.select(empresa);
            query.where(predicate);
            Empresa getEmpresa = session.createQuery(query).getSingleResult();
            closeSession(session);
            if (getEmpresa != null) {
                return getEmpresa;
            }else{
                return null;
            }
        }catch (Exception e){
            System.err.println("EmpresaDaoFactory method findOne(Long id): " + e.toString());
            return null;
        }
    }


    private String findOneByRut(String rut) {
        try {
            Session session = openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Empresa> query = cb.createQuery(Empresa.class);
            Root<Empresa> empresa = query.from(Empresa.class);
            Predicate predicate = cb.and(
                    cb.equal(empresa.get("rut"), rut));
            query.select(empresa);
            query.where(predicate);
            Empresa getEmpresa = session.createQuery(query).uniqueResult();
            closeSession(session);
            if (getEmpresa != null) {
                return getEmpresa.getRut();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("ClienteDaoFactory method findOne(Long id): " + e.toString());
            return null;
        }
    }

    @Override
    public boolean delete(Empresa empresa) {
        empresa.setBorrado(true);
        return deleteEntity(empresa);
    }




    @Override
    public Empresa update(Long id, Empresa empresa) {
        return (Empresa) updateEntity(empresa);
    }

    @Override
    public List<Empresa> getAllEmpresa() {
        try {
            Session session = openSession();
            CriteriaQuery<Empresa> query = session.getCriteriaBuilder().createQuery(Empresa.class);
            query.from(Empresa.class);
            List<Empresa> listClientes = session.createQuery(query).getResultList();
            closeSession(session);
            return listClientes;
        }catch (Exception e){
            System.err.println("ClienteDaoFactory method getAllEmpresa(): " + e.toString());
            return null;
        }
    }
}
