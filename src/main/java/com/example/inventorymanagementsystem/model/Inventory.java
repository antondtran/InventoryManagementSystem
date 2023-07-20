package com.example.inventorymanagementsystem.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public Inventory(){
        allParts = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
    }

    /**
     *
     * @param newPart method takes in a part and adds it to the parts list(allParts)
     */

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     *
     * @param newProduct method takes in a product and adds it to the products list(allProducts)
     */

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }


    /**
     *
     * @param partID takes in a parameter input for partID.
     * @return retrieves the part ID
     */

    public Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (part.getId() == partID) {
                return part;
            }
        }
        return null; // Part with the given ID not found
    }

    /**
     *
     * @param partName input parameter to find a part by name
     * @return returns the part name that matches with the part that was passed into the parameter
     */

    public Part lookupPart(String partName) {
        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                return part;
            }
        }
        return null; // Part with the given name not found
    }

    /**
     *
     * @param productID takes in a parameter input for partID.
     * @return retrieves the product ID
     */

    public Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                return product;
            }
        }
        return null; // Part with the given ID not found
    }

    /**
     *
     * @param productName input parameter to find a part by name
     * @return returns the part name that matches with the part that was passed into the parameter
     */

    public Product lookupProduct(String productName) {
        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null; // Part with the given name not found
    }


    /**
     *
     * @param index parameter input that retrieves the part ID
     * @param selectedPart parameter input for the specified part
     * method retrieves the part to be updated based on part ID
     */

    public void updatePart(int index, Part selectedPart){
        int indexToUpdate = -1;

        // Find the index of the part with the given ID in the allParts list
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == index) {
                indexToUpdate = i;
                break;
            }
        }

        // If the part is found, update it at the correct index
        if (indexToUpdate != -1) {
            allParts.set(indexToUpdate, selectedPart);
        }

    }

    /**
     *
     * @param index parameter input that retrieves the part ID
     * @param newProduct parameter input for the specified part
     * method retrieves the part to be updated based on part ID
     */

    public void updateProduct(int index, Product newProduct){
        int indexToUpdate = -1;

        // Find the index of the part with the given ID in the allParts list
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == index) {
                indexToUpdate = i;
                break;
            }
        }

        // If the part is found, update it at the correct index
        if (indexToUpdate != -1) {
            allProducts.set(indexToUpdate, newProduct);
        }
    }

    /**
     *
     * @param selectedPart paremeter input for a specified part
     * @return returns true when part gets deleted from table view.
     */

    public boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        return true;

    }

    /**
     *
     * @param selectedProduct paremeter input for a specified product
     * @return returns true when product gets deleted from table view.
     */

    public boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;

    }


    /**
     *
     * @return retrieves a list of parts
     */

    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     *
     * @return retrieves a list of products
     */

    public ObservableList<Product> getAllProducts() {
        return allProducts;

    }
}

