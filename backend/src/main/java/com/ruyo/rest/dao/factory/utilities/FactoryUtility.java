package com.ruyo.rest.dao.factory.utilities;

import com.ruyo.rest.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class FactoryUtility<T> extends Calculo{

    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession(){
        if(sessionFactory.isClosed()){
            return sessionFactory.openSession();
        }
        return sessionFactory.openSession();
    }

    private void closeSession(Session sessionStatus){
        if(sessionStatus.isOpen()){
            sessionStatus.close();
        }
    }


    protected T addEntity(T o) {
        try{
            Session session = openSession();
            Transaction transaction = session.beginTransaction();
            if (transaction.isActive()) {
                session.save(o);
                transaction.commit();
                closeSession(session);
                return o;
            }
        }catch(Exception e){
            System.err.println("FactoryUtility<T> protected T addEntity(T o): " + e.toString());
            return o;
        }
        return o;
    }


    protected boolean deleteEntity(T o){
        try {
            Session session = openSession();
            Transaction transaction = session.beginTransaction();
            if (transaction.isActive()) {
                session.update(o);
                transaction.commit();
                closeSession(session);
                return true;
            }
            return false;
        }catch(Exception e){
            System.err.println("FactoryUtility<T> method boolean deleteEntity(T object): " + e.toString());
            return false;
        }
    }

    protected T updateEntity(T o) {
        try {
            Session session = openSession();
            Transaction transaction = session.beginTransaction();
            if (transaction.isActive()) {
                session.update(o);
                transaction.commit();
                closeSession(session);
                return o;
            }
            return null;
        }catch(Exception e){
            System.err.println("FactoryUtility<T> method boolean deleteEntity(T object): " + e.toString());
            return null;
        }
    }

    protected Cliente getClienteByParams(String usuario, String contrasena){
        try {
            if (contrasena != null | usuario != null) {
                Session session = openSession();
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
                Root<Cliente> clienteRoot = query.from(Cliente.class);

                Predicate predicate = cb.and(
                        cb.equal(clienteRoot.get("usuario"), usuario),
                        cb.equal(clienteRoot.get("contrasena"), contrasena));
                query.select(clienteRoot).where(predicate);
                Cliente cliente = session.getSession().createQuery(query).getSingleResult();
                closeSession(session);
                return cliente;
            }
        }catch (NoResultException e){
            return null;
        }
        return null;
    }

    protected Cliente getClienteByEmail(String correo){
        try {
            if (correo != null) {
                Session session = openSession();
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
                Root<Cliente> clienteRoot = query.from(Cliente.class);
                Predicate predicate = cb.and(
                        cb.equal(clienteRoot.get("correo"), correo));
                query.select(clienteRoot).where(predicate);
                Cliente cliente = session.getSession().createQuery(query).getSingleResult();
                closeSession(session);
                return cliente;
            }
        }catch (NoResultException e){
            return null;
        }
        return null;
    }


    protected Cliente getClienteByUser(String usuario){
        try {
            if (usuario != null) {
                Session session = openSession();
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
                Root<Cliente> clienteRoot = query.from(Cliente.class);
                Predicate predicate = cb.and(
                        cb.equal(clienteRoot.get("usuario"), usuario));
                query.select(clienteRoot).where(predicate);
                Cliente cliente = session.getSession().createQuery(query).getSingleResult();
                closeSession(session);
                return cliente;
            }
        }catch (NoResultException e){
            return null;
        }
        return null;
    }

    protected List getCoordenadas(Long gps_id){

        Session session = openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Coordenada> query = cb.createQuery(Coordenada.class);
        Root<Coordenada> CoordenadaRoot = query.from(Coordenada.class);
        Predicate predicate = cb.and(
                cb.equal(CoordenadaRoot.get("gps"), gps_id));
        query.select(CoordenadaRoot).where(predicate);
        List coordenada = session.getSession().createQuery(query).getResultList();
        closeSession(session);

        return coordenada;

    }


    protected List getCelularesByIdClient(Long cliente_id){
        Session session = openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Celular> query = cb.createQuery(Celular.class);
        Root<Celular> CelularRoot = query.from(Celular.class);
        Predicate predicate = cb.and(
                cb.equal(CelularRoot.get("cliente"), cliente_id));
        query.select(CelularRoot).where(predicate);
        List celulares = session.getSession().createQuery(query).getResultList();
        closeSession(session);

        return celulares;

    }

    protected Vehiculo getVehiculoByIdGps(Long gps_id){

        Session session = openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Vehiculo> query = cb.createQuery(Vehiculo.class);
        Root<Vehiculo> VehiculoRoot = query.from(Vehiculo.class);
        Predicate predicate = cb.and(
                cb.equal(VehiculoRoot.get("gps"), gps_id));
        query.select(VehiculoRoot).where(predicate);

        Vehiculo vehiculo = session.getSession().createQuery(query).getSingleResult();
        closeSession(session);

        return vehiculo;

    }

}
