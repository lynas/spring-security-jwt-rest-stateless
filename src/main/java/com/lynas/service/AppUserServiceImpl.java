package com.lynas.service;

import com.lynas.model.AppUser;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "appUserService")
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public AppUser findByUsername(String username) {
        return (AppUser) sessionFactory.getCurrentSession()
                .createCriteria(AppUser.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }

    @Transactional
    public long create(AppUser appUser) {
        return (long) sessionFactory.getCurrentSession().save(appUser);
    }

    @Transactional
    public AppUser findById(long id) {
        return sessionFactory.getCurrentSession().get(AppUser.class, id);
    }

    @Transactional
    public AppUser update(AppUser appUser) {
        sessionFactory.getCurrentSession().update(appUser);
        return findById(appUser.getId());
    }

    @Transactional
    public boolean delete(long id) {
        sessionFactory.getCurrentSession().delete(findById(id));
        return true;
    }
}
