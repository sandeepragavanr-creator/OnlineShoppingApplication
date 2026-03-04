package onlineshoppingplatform.decorator;

import onlineshoppingplatform.enums.ProductCategory;
import onlineshoppingplatform.models.Product;

public abstract class ProductDecorator extends Product {
    protected Product decoratedProduct;

    public ProductDecorator(Product product){
        this.decoratedProduct = product;
    }

    public  String getId(){
        return decoratedProduct.getId();
    }
    public  String getName(){
        return decoratedProduct.getName();
    }
    public  String getDescription(){
        return decoratedProduct.getDescription();
    }
    public  double getPrice(){
        return decoratedProduct.getPrice();
    }
    public ProductCategory getProductCategory(){
        return decoratedProduct.getProductCategory();
    }
}
