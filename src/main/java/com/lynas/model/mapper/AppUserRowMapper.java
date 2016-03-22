package com.lynas.model.mapper;

import com.lynas.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by LynAs on 20-Mar-16
 */
public class AppUserRowMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet resultSet, int currentRow) throws SQLException {
        AppUser appUser = new AppUser();
        appUser.setId(resultSet.getLong("id"));
        appUser.setUsername(resultSet.getString("username"));
        appUser.setPassword(resultSet.getString("password"));
        appUser.setLastPasswordReset(resultSet.getDate("last_password_reset"));
        appUser.setAuthorities(resultSet.getString("authorities"));
        appUser.setEmail(resultSet.getString("email"));
        return appUser;
    }
}
