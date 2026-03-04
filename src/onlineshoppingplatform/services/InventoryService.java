package onlineshoppingplatform.services;

import onlineshoppingplatform.exceptions.OutOfStockException;
import onlineshoppingplatform.models.OrderLineItem;
import onlineshoppingplatform.models.Product;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InventoryService {

    private final Map<String,Integer> stock;

    public InventoryService(Map<String, Integer> stock) {
        this.stock = new ConcurrentHashMap<>();
    }

    public void addStock(Product product, int quantity){
        stock.put(product.getId(), stock.getOrDefault(product.getId(),0) + quantity);
    }

    public synchronized void updateStockForOrder(List<OrderLineItem> items){
        for(OrderLineItem item : items){
            if (stock.getOrDefault(item.getProductId(), 0) < item.getQuantity()){
                throw new OutOfStockException("Product out of stock for the productId " + item.getProductId());

            }
        }
        for (OrderLineItem item : items){
            stock.put(item.getProductId(), stock.get(item.getProductId()) - item.getQuantity());
        }
    }
}
