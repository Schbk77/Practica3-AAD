package modelo;

import hibernate.NewHibernateUtil;
import hibernate.Inmuebles;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ModeloInmuebles {
    
    public static List<Inmuebles> get() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "from Inmuebles";
        Query q = session.createQuery(hql);
        List<Inmuebles> inmuebles = q.list();
        session.getTransaction().commit();
        session.close();
        return inmuebles;
    }
    
    public static Inmuebles get(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Inmuebles in = (Inmuebles)session.get(Inmuebles.class, Integer.parseInt(id));
        session.getTransaction().commit();
        session.close();
        return in;
    }
    
    public static void delete(String id) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Inmuebles in = (Inmuebles)session.load(Inmuebles.class, Integer.parseInt(id));
        session.delete(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void insert(Inmuebles in){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
    
    public static void edit(Inmuebles in){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(in);
        session.getTransaction().commit();
        session.flush();
        session.close();
    }
}