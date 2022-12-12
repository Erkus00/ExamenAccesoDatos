package dao;

import models.Ejemplar;
import models.Libro;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {

    private static SessionFactory sessionFactory;

    static {
        try {

            Configuration config = new Configuration();
            config.configure();
            sessionFactory = config.buildSessionFactory();

            System.out.println("Conexión Realizada con Exito");
        } catch (Exception ex) {
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void saveLibro(Libro e) {
        ArrayList<Ejemplar> ejemplars = e.getEjemplars();
        /* Guarda un libro con todos sus ejemplares en la base de datos */
        Long id;

        try (Session session = sessionFactory.openSession()) {
            Transaction tst = session.beginTransaction();
            session.persist(e);
            tst.commit();
        } catch (HibernateException f) {
            throw new RuntimeException(f);
        }

        try (Session session = sessionFactory.openSession()) {
            String sql_query = "select max(lib.id) FROM Libro lib";
            id = session.createQuery(sql_query, Long.class).uniqueResult();
            e.setId(Long.valueOf(id));

        } catch (HibernateException k) {
            throw new RuntimeException(k);
        }


        System.out.println("El libro se con la Nueva Id es: " + id);

        ejemplars.forEach(k -> {
            try (Session session = sessionFactory.openSession()) {
                Transaction tst = session.beginTransaction();
                session.persist(k);
                tst.commit();
            } catch (HibernateException f) {
                throw new RuntimeException(f);
            }
        });
    }

    public HashSet<Libro> findByEstado(String estado) {

        HashSet<Libro> salida = new HashSet<Libro>();
        ArrayList<Libro> salida_list = new ArrayList<>();
        String hql_query = "SELECT DISTINCT(libro_asociado) FROM Ejemplar ej WHERE estado=:i";
        try (Session session = sessionFactory.openSession()) {
            Query<Libro> query = session.createQuery(hql_query, Libro.class);
            query.setParameter("i", estado);
            salida_list = (ArrayList<Libro>) query.list();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        salida_list.forEach(k -> {
            if (!salida.contains(k)) {
                salida.add(k);
            }
        });

        return salida;
    }

    public void printInfo() {
        
        /* 
          Muestra por consola todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Debe imprimirlo de esta manera:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */

        String hql_query = "FROM Libro";
        ArrayList<Libro> libros = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(hql_query, Libro.class);
            libros = (ArrayList<Libro>) query.list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<Libro> distintc_libro = new ArrayList<>();
        libros.forEach(k -> {
            if (!distintc_libro.contains(k)) {
                distintc_libro.add(k);
            }
        });

        System.out.println("BIBLIOTECA");
        System.out.println("----------");
        distintc_libro.forEach(k -> {
            System.out.print(k.getTitulo());
            try (Session session = sessionFactory.openSession()) {
                String hql_query2 = "select count(id) from Ejemplar WHERE libro_asociado=:param";
                Query query = session.createQuery(hql_query2, Long.class);
                query.setParameter("param", k);
                Integer value = query.getFirstResult();
                System.out.println(" (" + value + ")");
            }
        });

    }

}
