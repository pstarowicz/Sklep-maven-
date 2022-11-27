package pl.camp.it.sklep.database;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import pl.camp.it.sklep.model.User;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDB implements IUserDB{
    private final Map<String,User> users = new HashMap<>();

    public UserDB() {

    }

    public User findUserByLogin(String login){
        return this.users.get(login);
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
