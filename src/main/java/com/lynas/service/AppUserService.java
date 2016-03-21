package com.lynas.service;

import com.lynas.model.AppUser;

/**
 * Created by sazzad on 2/11/16.
 */
public interface AppUserService {
    void insertAppUser(AppUser appUser);

    AppUser getUserByUserName(String userName);
}
