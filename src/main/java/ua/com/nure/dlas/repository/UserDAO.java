package ua.com.nure.dlas.repository;

import ua.com.nure.dlas.model.User;

public interface UserDAO {

    User getUser(String username) throws Exception;

}
