package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.User;

import java.util.Map;

public interface IUserDB {
    Map<String, User> getUsers();
    User findUserByLogin(String login);
}
