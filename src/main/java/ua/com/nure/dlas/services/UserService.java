package ua.com.nure.dlas.services;

import ua.com.nure.dlas.model.User;

public interface UserService {

    User getUser(String username) throws Exception;

}
