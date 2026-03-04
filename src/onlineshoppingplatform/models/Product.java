package onlineshoppingplatform.models;

import onlineshoppingplatform.enums.ProductCategory;

import java.util.UUID;

public abstract class Product {
    protected String id;
    protected String name;
    protected String description;
    protected double price;
    protected ProductCategory productCategory;

    public abstract String getId();
    public abstract String getName();
    public abstract String getDescription();
    public abstract double getPrice();
    public abstract ProductCategory getProductCategory();

    //BaseProduct Class
    public static class BaseProduct extends Product{

        BaseProduct(String id, String name, String description, double price, ProductCategory productCategory){
            this.id = id;
            this.name =name;
            this.description = description;
            this.price = price;
            this.productCategory=productCategory;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public double getPrice() {
            return price;
        }

        @Override
        public ProductCategory getProductCategory() {
            return productCategory;
        }
    }

    //Builder Class
    public static class Builder {
        private final String name;
        private final double price;
        private String description = "";
        private ProductCategory productCategory;

        public Builder(String name, double price){
            this.name = name;
            this.price = price;
        }
        public Builder withDescription(String description){
            this.description = description;
            return this;
        }
        public Builder withProductCategory(ProductCategory productCategory){
            this.productCategory = productCategory;
            return this;
        }
        public Product build(){
            return new BaseProduct(UUID.randomUUID().toString(), name,description,price,productCategory);
        }

    }
}








