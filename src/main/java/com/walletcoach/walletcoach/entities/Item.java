package com.walletcoach.walletcoach.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
    private Long ID;
    
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    private Date datetime;

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

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public Date getDatetime() {
	return datetime;
    }

    public void setDatetime(Date datetime) {
	this.datetime = datetime;
    }  
}
