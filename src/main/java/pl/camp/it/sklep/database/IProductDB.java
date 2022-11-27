package pl.camp.it.sklep.database;

import pl.camp.it.sklep.model.Product;

import java.util.List;

public interface IProductDB {
    Product returnProduct(String name);
    List<Product> getProducts();
}
