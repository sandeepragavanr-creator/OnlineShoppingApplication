package onlineshoppingplatform.models;

public class OrderLineItem {

    private final String productId;
    private final String productName;
    private final int quantity;
    private final double PriceAtPurchase;

    public OrderLineItem(String productId, String productName, int quantity, double priceAtPurchase){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.PriceAtPurchase = priceAtPurchase;
    }

    public String getProductId(){
        return productId;
    }

    public String getProductName(){
        return productName;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getPriceAtPurchase(){
        return PriceAtPurchase;
    }
}
