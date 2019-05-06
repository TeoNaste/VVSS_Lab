package agenda.repository.classes;

import agenda.model.base.User;
import agenda.repository.interfaces.RepositoryUser;

import java.util.List;

public class RepositoryUserMock implements RepositoryUser {


    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public boolean changePasswd(User user, String oldPasswd, String newPasswd) {
        return false;
    }

    @Override
    public boolean save() {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
