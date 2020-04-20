package com.maaza.quick.user;

import java.util.List;

public class UserService {

    private static final UserManager MGR = new UserManager();

    public List<User> list() throws Exception {
        return MGR.selectAll();
    }

    public User find(Integer id) throws Exception {
        return MGR.select(id);
    }

    public void drop(Integer id) throws Exception {
        MGR.delete(id);
    }

    public void save(User user) throws Exception {
        if (user.getId() != null) {
            MGR.update(user);
        } else {
            MGR.insert(user);
        }
    }
}
