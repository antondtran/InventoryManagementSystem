package com.example.inventorymanagementsystem.model;

public class Outsourced extends Part{

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *
     * @return retrieves the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName sets the company name
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
