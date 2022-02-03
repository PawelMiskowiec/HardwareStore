package pl.edu.wszib.hardwareStore.database.impl.hibernate;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.hardwareStore.database.IProductDAO;
import pl.edu.wszib.hardwareStore.model.Product;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ProductDAOimpl implements IProductDAO {
    private final SessionFactory sessionFactory;

    @Override
    public List<Product> getProducts() {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.hardwareStore.model.Product");
        List<Product> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Product> getById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.hardwareStore.model.Product WHERE id = :id");
        query.setParameter("id", id);
        try {
            Product product = query.getSingleResult();
            session.close();
            return Optional.of(product);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getByCategory(String category) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.hardwareStore.model.Product WHERE lower(:category) like lower(category)");
        query.setParameter("category", category);
        List<Product> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(product);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

}
