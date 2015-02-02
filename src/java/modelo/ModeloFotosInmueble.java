package modelo;

import hibernate.FotosInmueble;
import hibernate.NewHibernateUtil;
import java.util.List;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ModeloFotosInmueble {
    
    public static List<FotosInmueble> get() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from FotosInmueble";
        Query q = session.createQuery(hql);
        List<FotosInmueble> inmuebles = q.list();
        session.getTransaction().commit();
        session.close();
        return inmuebles;
    }
    
    public static FotosInmueble get(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        FotosInmueble in = (FotosInmueble)session.get(FotosInmueble.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return in;
    }
    
    public static void delete(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        FotosInmueble in = (FotosInmueble)session.load(FotosInmueble.class, Integer.parseInt(id));
        session.delete(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void deleteAll(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "delete from FotosInmueble where id_inmueble = :id_inmueble";
        Query q = session.createQuery(hql);
        q.setParameter("id_inmueble", id);
        int result = q.executeUpdate();
        System.out.println(result + " filas afectadas");
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(FotosInmueble in){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(FotosInmueble in){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}
