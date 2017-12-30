package com.iss.factory;

import com.iss.service.IUserService;
import com.iss.service.impl.UserServiceImpl;

public class ServiceFactory {
    public static IUserService getIUserServiceInstance(){
        return new UserServiceImpl();
    }
}
