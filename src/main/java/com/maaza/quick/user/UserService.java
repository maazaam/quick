package com.maaza.quick.user;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static final UserManager MGR = new UserManager();

    public List<User> list() throws SQLException {
        return MGR.selectAll();
    }

    public User find(Integer id) throws SQLException {
        return MGR.select(id);
    }

    public void drop(Integer id) throws SQLException {
        MGR.delete(id);
    }

    public void save(User user) throws SQLException {
        if (user.getId() != null) {
            MGR.update(user);
        } else {
            MGR.insert(user);
        }
    }
}
