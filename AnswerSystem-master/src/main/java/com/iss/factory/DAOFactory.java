package com.iss.factory;

import com.iss.dao.IUserDAO;
import com.iss.dao.impl.UserDAOImpl;

import java.sql.Connection;

public class DAOFactory {
    public static IUserDAO getIUserDAOInstance(Connection conn){
        return new UserDAOImpl(conn);
    }
}
