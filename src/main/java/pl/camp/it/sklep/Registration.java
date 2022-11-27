package pl.camp.it.sklep;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.User;

import java.util.Scanner;

@Component
public class Registration {
    public void registrate(UserDB userDB){
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<3;i++) {
            System.out.println(">>Rejestracja<<");
            System.out.println("Podaj login:");
            String login = scanner.nextLine();
            if (userDB.getUsers().containsKey(login)) {
                System.out.println("Konto o podanym loginie już istnieje");
            } else {
                System.out.println("Podaj hasło");
                userDB.getUsers().put(login, new User(login, DigestUtils.md5Hex(scanner.nextLine() + Authenticator.seed), User.Role.USER));
                System.out.println("Zarejestrowano pomyślnie");
                i=3;
            }
        }
    }
}
