package com.example.inventorymanagementsystem.model;

import javafx.collections.ObservableList;

public class Product {



    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /**
     *
     * @return returns a list of associated parts
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     *
     * @param associatedParts method takes in a list of associated parts and assigns to the associated parts field.
     */

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     *
     * @return get ID retrieves and returns the product id
     */

    public int getId() {
        return id;
    }

    /**
     *
     * @param id sets the product id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return retrieves the product name
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @param name sets the product name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return retrieves the product price
     */

    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price sets the product price
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return retrieves the product inventory level
     */

    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock sets the product inventory level
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return retrieves the product minimum value
     */

    public int getMin() {
        return min;
    }

    /**
     *
     * @param min sets the product minimum value
     */

    public void setMin(int min) {
        this.min = min;
    }

    /**
     *
     * @return retrieves the product max value
     */

    public int getMax() {
        return max;
    }

    /**
     *
     * @param max sets the product max value
     */

    public void setMax(int max) {
        this.max = max;
    }
}
