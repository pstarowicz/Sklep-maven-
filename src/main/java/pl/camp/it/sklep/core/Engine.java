package pl.camp.it.sklep.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.DBManager;
import pl.camp.it.sklep.GUI.GUI;
import pl.camp.it.sklep.Registration;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.User;

import java.util.Scanner;

@Component
public class Engine {

    @Autowired
    private Registration registration;
    @Autowired
    private Authenticator authenticator;
    @Autowired
    private GUI gui;
    @Autowired
    private ProductDB productDB;
    @Autowired
    private UserDB userDB;
    @Autowired
    private DBManager dbManager;

    public void start(){
        dbManager.getFromFile(productDB,userDB);
        Scanner scanner = new Scanner(System.in);

        boolean isWorking=false;
        boolean isNotLogged=true;
        while(isNotLogged) {
            System.out.println("1. Rejestracja");
            System.out.println("2. Logowanie");
            System.out.println("3. Wyjście");
            switch(scanner.nextLine()) {
                case "1":
                    registration.registrate(userDB);
                    break;
                case "2":
                    isWorking= authenticator.authenticate(userDB);
                    isNotLogged=!isWorking;
                    break;
                case "3":
                    isNotLogged=false;
                    dbManager.persistToFile(productDB,userDB);
                    break;
                default:
                    System.out.println("Nieprawidłowy numer");
                    break;
            }
        }

        System.out.println();

        while(isWorking){
            gui.showMenu();
            switch(scanner.nextLine()){
                case "1":
                    gui.listProducts(productDB.getProducts());
                    break;
                case "2":
                    gui.buyProduct(productDB);
                    break;
                case "5":
                    isWorking=false;
                    dbManager.persistToFile(productDB,userDB);
                    break;
                case "3":
                    if(Authenticator.loggedUser.getRole()== User.Role.ADMIN){
                        gui.addProductAmount(productDB);
                        break;
                    }
                case "4":
                    if(Authenticator.loggedUser.getRole()==User.Role.ADMIN){
                        gui.changeUserToAdmin(userDB);
                        break;
                    }
                default:
                    System.out.println("Zły numer!");
                    break;
            }
        }
    }
}
