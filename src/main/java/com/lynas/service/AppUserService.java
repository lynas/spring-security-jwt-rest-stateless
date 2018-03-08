package com.lynas.service;

import com.lynas.model.AppUser;

/**
 * Created by LynAs on 20-Mar-16
 */
public interface AppUserService {
    AppUser findById(long id);

    AppUser findByUsername(String username);

    long create(AppUser appUser);

    AppUser update(AppUser appUser);

    boolean delete(long id);
}
