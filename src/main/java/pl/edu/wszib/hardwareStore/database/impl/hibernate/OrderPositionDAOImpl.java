package pl.edu.wszib.hardwareStore.database.impl.hibernate;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.hardwareStore.database.IOrderPositionsDAO;
import pl.edu.wszib.hardwareStore.model.OrderPosition;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderPositionDAOImpl implements IOrderPositionsDAO {
    private final SessionFactory sessionFactory;

    @Override
    public void addOrderPosition(OrderPosition orderPosition) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(orderPosition);
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
    public List<OrderPosition> getOrderPositionsByOrderId(int orderId) {
        return null;
    }
}
