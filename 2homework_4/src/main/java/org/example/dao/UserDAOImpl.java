package org.example.dao;

import org.example.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Implementation of UserDAO interface utilizing Hibernate ORM for performing CRUD operations on User entities.
 */
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    /**
     * Constructs a new instance of UserDAOImpl and initializes the Hibernate session factory.
     * 
     * @throws HibernateException if there's an issue while initializing Hibernate session factory.
     */
    public UserDAOImpl() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw new HibernateException("Hibernate's initialization failed!", e);
        }
    }

    /**
     * Retrieves a user by its id.
     *
     * @param id the unique identifier of the user to fetch.
     * @return the User object associated with the given ID.
     * @throws Exception if the user could not be found or another problem occurs.
     */
    @Override
    public User getUserById(Long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        } catch (Exception e) {
            throw new Exception("Invalid id!", e);
        }
    }

    /**
     * Get all users from the db.
     *
     * @return a list of all User objects.
     * @throws Exception if problems arise while fetching users from the db.
     */
    @Override
    public List<User> getAllUsers() throws Exception {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            throw new Exception("Problems with database connection!", e);
        }
    }

    /**
     * Saves a new user to the db.
     *
     * @param user the User object to persist.
     * @throws Exception if problems arise while saving the user.
     */
    @Override
    public void saveUser(User user) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            throw new Exception("Problems with adding new user!", e);
        }
    }

    /**
     * Updates an existing user in the db.
     *
     * @param user the User object with updated attributes.
     * @throws Exception if the user couldn't be found or other problems occurred.
     */
    @Override
    public void updateUser(User user) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User existingUser = session.get(User.class, user.getId());

            if (existingUser == null) {
                throw new Exception("User is not found!");
            }

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAge(user.getAge());
            existingUser.setCreatedAt(user.getCreatedAt());
            session.update(existingUser);
            transaction.commit();
        } catch (Exception e) {
            throw new Exception("Problems with updating user!", e);
        }
    }

    /**
     * Deletes a user from the db by its id.
     *
     * @param id the unique identifier of the user to delete.
     * @throws Exception if the user couldn't be found or other problems occurred.
     */
    @Override
    public void deleteUser(Long id) throws Exception {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);

            if (user == null) {
                throw new Exception("User is not found!");
            }

            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            throw new Exception("Problems with deleting user!", e);
        }
    }
}
