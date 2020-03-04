package old.geot.dao.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Connection;

@Transactional
public class DaoFactoryUtility<T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    public Connection con;

    protected Session openSession(){
        if(sessionFactory.isClosed()){
            return sessionFactory.openSession();
        }
        return sessionFactory.openSession();
    }

    protected void closeSession(Session sessionStatus){
        if(sessionStatus.isOpen()){
            sessionStatus.flush();
            sessionStatus.getTransaction().commit();
            sessionStatus.close();
        }
    }



    protected void openTransaccion(Session session){
        session.beginTransaction();
    }



    protected boolean addEntity(T object) {
        try{
            Session session = openSession();
            Transaction transaction = session.beginTransaction();
            if (transaction.isActive()) {
                session.save(object);
                session.flush();
                transaction.commit();
                closeSession(session);
                return true;
            }
        }catch(Exception e){
            System.err.println("FactoryUtility<T> method boolean addEntity(T object): " + e.toString());
            return false;
        }
        return false;
    }

    protected boolean deleteEntity(T object){
        try {
            Session session = openSession();
            Transaction transaction = session.beginTransaction();
            if (transaction.isActive()) {
                session.update(object);
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

    public T updateEntity(T object) {
        try {
            Session session = openSession();
            Transaction transaction = session.beginTransaction();
            if (transaction.isActive()) {
                session.update(object);
                transaction.commit();
                closeSession(session);
                return object;
            }
            return null;
        }catch(Exception e){
            System.err.println("FactoryUtility<T> method boolean deleteEntity(T object): " + e.toString());
            return null;
        }
    }

}
