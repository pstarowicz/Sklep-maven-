package pl.camp.it.sklep.GUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.sklep.Authenticator;
import pl.camp.it.sklep.database.ProductDB;
import pl.camp.it.sklep.database.UserDB;
import pl.camp.it.sklep.model.Product;
import pl.camp.it.sklep.model.User;

import java.util.List;
import java.util.Scanner;

@Component
public class GUI {
    public void showMenu(){
        System.out.println();
        System.out.println("1. Wyświetl listę produktów");
        System.out.println("2. Kup produkt");
        if (Authenticator.loggedUser.getRole() == User.Role.ADMIN) {

            System.out.println("3. Uzupełnij produkt");
            System.out.println("4. Dodaj admina");
        }
        System.out.println("5. Wyjście");
    }

    public void listProducts(List<Product> products){
        for(Product currentProduct:products){
            if(currentProduct.getAmount()!=0) {
                System.out.println(currentProduct);
            }
        }
    }

    public void buyProduct(ProductDB productDB){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę produktu:");
        Product product = productDB.returnProduct(scanner.nextLine());
        if(product!=null){
            System.out.println("Podaj ilość sztuk do kupienia (maks. " + product.getAmount() + "):");
            int wanted=Integer.parseInt(scanner.nextLine());
            if(wanted<=product.getAmount() && wanted>=0){
                System.out.println("Kwota zakupu wynosi: "+ product.getPrice() * ((double) wanted) +"zł");
                product.setAmount(product.getAmount()-wanted);
            }
            else if(wanted>product.getAmount()){
                System.out.println("Podano za dużą ilość!");
            }
            else{
                System.out.println("Podano niewłaściwą ilość!");
            }
        }
        else System.out.println("Brak podanego produktu w bazie");
    }

    public void addProductAmount(ProductDB productDB){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę produktu:");
        Product product = productDB.returnProduct(scanner.nextLine());
        if(product!=null){
            System.out.println("Podaj ilość sztuk do dodania (akt. "+product.getAmount()+"):");
            int added=Integer.parseInt(scanner.nextLine());
            if(added>=0){
                product.setAmount(product.getAmount()+added);
            }
            else{
                System.out.println("Podano ujemną liczbę!");
            }
        }
        else{
            System.out.println("Brak podanego produktu w bazie");
        }
    }

    public User readLogAndPass(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(">>Logowanie<<");
        System.out.println("Login:");
        String login = scanner.nextLine();
        System.out.println("Hasło:");
        return new User(login,scanner.nextLine());
    }

    public void changeUserToAdmin(UserDB userDB){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj login użytkownika, aby nadać mu prawa admina:");
        User user=userDB.findUserByLogin(scanner.nextLine());
        if(user!=null){
            if(user.getRole()==User.Role.ADMIN){
                System.out.println("Podany użytkownik już jest adminem");
            }
            else{
                user.setRole(User.Role.ADMIN);
                System.out.println("Użytkownik otrzymał rolę admina");
            }
        }
        else{
            System.out.println("Brak użytkownika o podanym loginie!");
        }
    }
}
