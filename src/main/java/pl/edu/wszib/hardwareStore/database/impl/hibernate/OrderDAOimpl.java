package pl.edu.wszib.hardwareStore.database.impl.hibernate;

import lombok.AllArgsConstructor;
import net.bytebuddy.description.annotation.AnnotationList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.hardwareStore.database.IOrderDAO;
import pl.edu.wszib.hardwareStore.model.Cart;
import pl.edu.wszib.hardwareStore.model.Order;
import pl.edu.wszib.hardwareStore.model.Product;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderDAOimpl implements IOrderDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
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
    public List<Order> getOrdersByUserId(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.edu.wszib.hardwareStore.model.Order WHERE user.id = :id");
        query.setParameter("id", id);
        try {
            List<Order> order = query.getResultList();
            session.close();
            return order;
        } catch (NoResultException e) {
            session.close();
            return List.of();
        }
    }
}
