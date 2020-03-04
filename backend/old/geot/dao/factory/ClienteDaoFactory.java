package old.geot.dao.factory;

import com.ruyo.rest.geot.dao.ClienteDao;
import com.ruyo.rest.geot.dao.utility.DaoFactoryUtility;
import com.ruyo.rest.geot.entity.Cliente;
import com.ruyo.rest.geot.entity.Empresa;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository("clienteDao")
public class ClienteDaoFactory extends DaoFactoryUtility implements ClienteDao {
    /*
        private final String ADDUSUARIO = "addUsuario";
        private final String FINDONEUSUARIO = "findOneUsuario";
        private final String DELETEUSUARIO = "deleteUsuario";
        private final String GETALLCLIENTES = "getAllClientes";
        private final String VALIDATEUSUARIO = "validateUsuario";
    */
    @Override
    public boolean add(Cliente cliente) {
        String usuario = findOneByName(cliente.getUsuario());
        if(usuario == null){
            cliente.setClienteId(0L);
            cliente.setHabilitado(true);
            cliente.setBorrado(false);
            return addEntity(cliente);
        }else if(!usuario.equalsIgnoreCase(cliente.getUsuario())) {
            cliente.setClienteId(0L);
            cliente.setHabilitado(true);
            cliente.setBorrado(false);
            return addEntity(cliente);
        }
        return false;
        /*
        Session session = openSession();
        boolean execute = session.
                createNamedStoredProcedureQuery(ADDUSUARIO).
                setParameter("nombre", cliente.getNombre()).
                setParameter("apellido", cliente.getApellido()).
                setParameter("usuario", cliente.getUsuario()).
                setParameter("contrasena", cliente.getContrasena()).
                execute();
        closeSession(session);
        return execute;
        */
    }

    @Override
    public Cliente findOne(Long id) {
        try {
            Session session = openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
            Root<Cliente> cliente = query.from(Cliente.class);
            Predicate predicate = cb.and(
                    cb.equal(cliente.get("id"), id));
            query.select(cliente);
            query.where(predicate);
            Cliente getCliente = session.createQuery(query).getSingleResult();
            closeSession(session);
            if (getCliente != null) {
                return getCliente;
            }else{
                return null;
            }
        }catch (Exception e){
            System.err.println("ClienteDaoFactory method findOne(Long id): " + e.toString());
            return null;
        }



        /*
        Session session = openSession();
        StoredProcedureQuery query = session.
                createNamedStoredProcedureQuery(FINDONEUSUARIO).
                setParameter("id", id);
        boolean execute = query.execute();
        Cliente cliente = null;
        if (execute) {
            cliente = (Cliente) query.getSingleResult();
        }
        closeSession(session);
        return cliente;
        */
    }

    private String findOneByName(String usuario) {
        try {
            Session session = openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
            Root<Cliente> cliente = query.from(Cliente.class);
            Predicate predicate = cb.and(
                    cb.equal(cliente.get("usuario"), usuario));
            query.select(cliente);
            query.where(predicate);
            Cliente getCliente = session.createQuery(query).uniqueResult();
            closeSession(session);
            if (getCliente != null) {
                return getCliente.getUsuario();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("ClienteDaoFactory method findOne(Long id): " + e.toString());
            return null;
        }
    }







    @Override
    public boolean delete(Cliente cliente) {
        cliente.setBorrado(true);
        return deleteEntity(cliente);
        /*
        Session session = openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<Cliente> delete = cb.createCriteriaUpdate(Cliente.class);
        Root<Cliente> cliente = delete.from(Cliente.class);
        delete.set("isDelete", true).
                where(cb.equal(cliente.get("id"),id));
        session.createQuery(delete).executeUpdate();
        closeSession(session);
        return true;

        Session session = openSession();
        StoredProcedureQuery query = session.
                createNamedStoredProcedureQuery(DELETEUSUARIO);
        query.setParameter("id", id);
        boolean execute = query.execute();
        closeSession(session);
        return execute;
        */
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        return (Cliente) updateEntity(cliente);
        /*
        Session session = openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<Cliente> update = cb.createCriteriaUpdate(Cliente.class);
        Root<Cliente> clienteRoot = update.from(Cliente.class);
        update.set("nombre", cliente.getNombre()).
                set("apellido",cliente.getApellido()).
                set("contrasena",cliente.getContrasena()).
                set("isEnable", cliente.isEnable()).
                where(cb.greaterThanOrEqualTo(clienteRoot.get("id"), cliente.getId()));
        session.createQuery(update).executeUpdate();
        closeSession(session);
        return cliente;
        */
    }

    @Override
    public String getAllClientes() {
        try {
            Session session = openSession();
            openTransaccion(session);



            CriteriaQuery<Cliente> query = session.getCriteriaBuilder().createQuery(Cliente.class);
            query.from(Cliente.class);
            List<Cliente> listClientes = session.createQuery(query).list();

            CriteriaQuery<Empresa> query2 = session.getCriteriaBuilder().createQuery(Empresa.class);
            query2.from(Empresa.class);
            List<Empresa> listaEmpresa = session.createQuery(query2).list();

            for (Cliente listCliente : listClientes) {
                listCliente.setEmpresas(listaEmpresa);
                for (Empresa empresa : listaEmpresa) {
                }
            }


            XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
            xstream.ignoreUnknownElements();
            xstream.processAnnotations(Cliente.class);
            xstream.processAnnotations(Empresa.class);
            String xml = xstream.toXML(listClientes);

            closeSession(session);

            return xml;
        }catch (Exception e){
            System.err.println("ClienteDaoFactory method getAllClientes(): " + e.toString());
            return null;
        }
        /*
        Session session = openSession();
        StoredProcedureQuery spq = session.
                createNamedStoredProcedureQuery(GETALLCLIENTES);
        spq.execute();
        List resultList = spq.getResultList();
        closeSession(session);
        return resultList;
         */
    }




    @Override
    public boolean isClienteValid(String user, String password) {
        try {
            Session session = openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
            Root<Cliente> cliente = query.from(Cliente.class);
            Predicate predicate = cb.and(
                    cb.equal(cliente.get("usuario"), user),
                    cb.equal(cliente.get("contrasena"), password));
            query.select(cliente);
            query.where(predicate);
            Cliente getCliente = session.createQuery(query).getSingleResult();
            closeSession(session);
            return getCliente != null;
        }catch (Exception e){
            System.err.println("ClienteDaoFactory method isClienteValid((String user, String password): " + e.toString());
            return false;
        }

                /*
        Session session = openSession();
        boolean execute = session.
                createNamedStoredProcedureQuery(VALIDATEUSUARIO).
                setParameter("usuario_", user).
                setParameter("contrasena_", password).
                execute();
        closeSession(session);
        return execute;
          */

    }


}


/*
        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setNombre("dgfd");
        cliente.setApellido("fdgfd");
        cliente.setUsuario("sdf");
        cliente.setContrasena("sdf");
        cliente.setHabilitado(true);
        cliente.setBorrado(false);

            session.save(cliente);



        Empresa empresa = new Empresa();
        empresa.setNombre("432");
        empresa.setDireccion("324");
        empresa.setRazonsocial("234");
        empresa.setRut("342");
        empresa.setTelefono("3423");
        empresa.setHabilitado(true);
        empresa.setBorrado(false);

        empresa.setCliente(cliente);
        cliente.getEmpresas().add(empresa);

        session.save(empresa);

        session.getTransaction().commit();
        System.out.println("Done");



		CriteriaQuery<Cliente> query = session.getCriteriaBuilder().createQuery(Cliente.class);
		query.from(Cliente.class);
		List<Cliente> listClientes = session.createQuery(query).getResultList();

        CriteriaQuery<Empresa> query2 = session.getCriteriaBuilder().createQuery(Empresa.class);
        query2.from(Empresa.class);
        final List<Empresa> listEmpresa = session.createQuery(query2).getResultList();

        List<String> sdf = new ArrayList<>();

        listClientes.forEach(x -> x.setEmpresas(listEmpresa));

        XStream xstream = new XStream();
        xstream.ignoreUnknownElements("<cliente reference=\"../../..\"/>");
        xstream.alias("cliente", Cliente.class);
        xstream.alias("empresa", Empresa.class);

        String xml = xstream.toXML(listClientes);

        System.out.println(xml);
*/