package ua.com.nure.dlas.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nure.dlas.model.User;
import ua.com.nure.dlas.repository.UserDAO;
import ua.com.nure.dlas.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUser(String username) {
        return userDAO.getUser(username);
    }

    @Override
    public String getGroupName(String email) {
        return userDAO.getUserGroupName(email);
    }

}
