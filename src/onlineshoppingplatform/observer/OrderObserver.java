package onlineshoppingplatform.observer;

import onlineshoppingplatform.models.Order;

public interface OrderObserver {

    void update(Order order);
}
