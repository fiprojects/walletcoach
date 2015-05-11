package com.walletcoach.walletcoach.entities;

/**
 * The class Subject is a base class for various companies
 * where the user makes purchases.
 * 
 * The class contains basic entities for storing information
 * such as the name, IC, the company address and a description.
 * 
 * @author fajlo
 */
public class Subject {
    private Long ID;
    private String name;
    private String ic;
    private String address;
    private String description;

    public Long getID() {
	return ID;
    }

    public void setID(Long ID) {
	this.ID = ID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getIc() {
	return ic;
    }

    public void setIc(String ic) {
	this.ic = ic;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
    
    
}
