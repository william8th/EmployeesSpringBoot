package com.bjss.william.employees.dao.hibernate;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by William Heng(dev) on 29/10/15.
 */

public class GenericDao {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }
}
