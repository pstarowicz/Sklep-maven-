package pl.camp.it.sklep.database;

import org.springframework.stereotype.Component;
import pl.camp.it.sklep.model.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDB implements IProductDB{
    private final List<Product> products = new ArrayList<>();

    public ProductDB() {

    }

    public Product returnProduct(String name){
        for(Product currentProduct:this.products){
            if(currentProduct.getName().equals(name)) return currentProduct;
        }
        return null;
    }


    public List<Product> getProducts() {
        return products;
    }

}
