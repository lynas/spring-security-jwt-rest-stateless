package com.lynas.service.impl;

import com.lynas.model.AppUser;
import com.lynas.service.AppUserService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service(value = "appUserService")
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public AppUser loadUserByUsername(String username) {
        return (AppUser) sessionFactory.getCurrentSession()
                .createCriteria(AppUser.class)
                .add(Restrictions.eq("username", username))
                .uniqueResult();
    }
}
