package old.geot.dao.factory;

import com.ruyo.rest.geot.dao.ChoferDao;
import com.ruyo.rest.geot.dao.utility.DaoFactoryUtility;
import com.ruyo.rest.geot.entity.Chofer;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("choferDao")
public class ChoferDaoFactory extends DaoFactoryUtility implements ChoferDao{

    @Override
    public boolean add(Chofer chofer) {
        String rut = findOneByRut(chofer.getRut());
        if(rut == null) {
            chofer.setChoferId(0L);
            chofer.setHabilitado(true);
            chofer.setBorrado(false);
            return addEntity(chofer);
        } else if(!rut.equalsIgnoreCase(chofer.getRut())){
            chofer.setBorrado(false);
            chofer.setChoferId(0L);
            chofer.setHabilitado(true);

            return addEntity(chofer);
        }
        return false;

    }

    @Override
    public Chofer findOne(Long id) {
        return null;
    }

    private String findOneByRut(String rut) {
        try {
            Session session = openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Chofer> query = cb.createQuery(Chofer.class);
            Root<Chofer> chofer = query.from(Chofer.class);
            Predicate predicate = cb.and(
                    cb.equal(chofer.get("rut"), rut));
            query.select(chofer);
            query.where(predicate);
            Chofer getChofer = session.createQuery(query).uniqueResult();
            closeSession(session);
            if (getChofer != null) {
                return getChofer.getRut();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("ChoferDaoFactory method findOneByRut(String rut): " + e.toString());
            return null;
        }
    }

    @Override
    public boolean delete(Chofer chofer) {
        chofer.setBorrado(true);
        return deleteEntity(chofer);
    }

    @Override
    public Chofer update(Long id, Chofer chofer) {
        return (Chofer) updateEntity(chofer);
    }

    @Override
    public List getAllChoferes() {
        try {
            Session session = openSession();
            CriteriaQuery<Chofer> query = session.getCriteriaBuilder().createQuery(Chofer.class);
            query.from(Chofer.class);
            List<Chofer> listClientes = session.createQuery(query).getResultList();
            closeSession(session);
            return listClientes;
        }catch (Exception e){
            System.err.println("ChoferDaoFactory method getAllChoferes(): " + e.toString());
            return null;
        }
    }
}
