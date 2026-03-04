package onlineshoppingplatform.models;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String,CartItem> items = new HashMap<>();

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void addItem(Product product, int quantity){
        if(items.containsKey(product.getId())){
            items.get(product.getId()).incrementQuantity(quantity);
        }else {
            items.put(product.getId(),new CartItem(product, quantity));
        }
    }

    public void removeItem(String productId){
        items.remove(productId);
    }

    public double calculateTotal(){
        return items.values().stream().mapToDouble(CartItem::getPrice).sum();
    }
    public void clearCart(){
        items.clear();
    }
}
