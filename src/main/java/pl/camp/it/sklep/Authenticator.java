package pl.camp.it.sklep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.sklep.GUI.GUI;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.User;

@Component
public class Authenticator {
    public static User loggedUser;
    public static final String seed = "cdscdssdv434632*(&*)";
    @Autowired
    private GUI gui;

    public boolean authenticate(UserDB userDB){
        for(int i=0;i<3; i++){
            User user = gui.readLogAndPass();
            User userFromDB = userDB.findUserByLogin(user.getLogin());
            if(userFromDB != null && userFromDB.equals(user)){
                this.loggedUser = userFromDB;
                System.out.println("Zalogowano pomyślnie");
                return true;
            }
        }
        System.out.println("Błąd logowania");
        return false;
    }
}
