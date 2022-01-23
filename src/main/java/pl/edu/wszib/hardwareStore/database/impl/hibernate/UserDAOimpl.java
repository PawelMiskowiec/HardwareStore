package pl.edu.wszib.hardwareStore.database.impl.hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.hardwareStore.database.IUserDAO;
import pl.edu.wszib.hardwareStore.model.User;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAOimpl implements IUserDAO {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> getUserByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.hardwareStore.model.User WHERE login = :login");
        query.setParameter("login", login);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch (NoResultException e){
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.hardwareStore.model.User WHERE id = :id");
        query.setParameter("id", id);
        try {
            User user = query.getSingleResult();
            session.close();
            return Optional.of(user);
        } catch (NoResultException e){
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> addUser(User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(user);
            return Optional.of(user);
        } catch (NoResultException e){
            session.close();
            return Optional.empty();
        }
    }
}
