package pl.camp.it.sklep.model;

public class Product {
    private String name;
    private double price;
    private int amount;

    public Product(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Product(String[] params) {
        this(params[0],Double.parseDouble(params[1]),Integer.parseInt(params[2]));
    }

    public Product() {
    }

    @Override
    public String toString() {
        return new StringBuilder().append("nazwa: ")
                .append(this.name)
                .append(" -- cena[zł]: ")
                .append(this.price)
                .append(" -- ilość: ")
                .append(this.amount)
                .toString();
    }

    public String convertToData(){
        return new StringBuilder()
                .append(this.name)
                .append(";")
                .append(this.price)
                .append(";")
                .append(this.amount)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
