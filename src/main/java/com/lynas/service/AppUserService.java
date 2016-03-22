package com.lynas.service;

import com.lynas.model.AppUser;

/**
 * Created by LynAs on 20-Mar-16
 */
public interface AppUserService {
    AppUser loadUserByUsername(String username);
}
