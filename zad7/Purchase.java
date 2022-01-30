/**
 *
 *  @author Malicki Cezary S22434
 *
 */

package zad2;


public class Purchase {

    private String clientID;
    private String name;
    private String commodity;
    private double price;
    private double quantity;

    public Purchase(String text) {
        String[] fileLine = text.split(";");
        clientID = fileLine[0];
        name = fileLine[1];
        commodity = fileLine[2];
        price = Double.parseDouble(fileLine[3]);
        quantity = Double.parseDouble(fileLine[4]);
    }

    public String getClientID() {
        return clientID;
    }

    public String getName() {
        return name;
    }

    public String getCommodity() {
        return commodity;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCost() {
        return price * quantity;
    }

    public String toString() {
        return clientID + ";" + name + ";" + commodity + ";" + price + ";" + quantity;
    }

}
