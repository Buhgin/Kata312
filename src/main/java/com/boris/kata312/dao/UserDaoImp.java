package com.boris.kata312.dao;

import com.boris.kata312.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("SELECT u FROM User u",User.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();

    }

    @Override
    public void update(User user) {

        entityManager.merge(user);



    }
    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }


}


