package com.walletcoach.walletcoach.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * The class Item is a base class for various items
 * that are purchased by the user.
 * 
 * The class contains basic entities for storing information
 * such as the name, description, the price of the item and the time of purchase.
 * 
 * @author fajlo
 */

public class Item {
    private Long ID;
    private String name;
    private String description;
    private BigDecimal price;
    private Date datetime;

    /**
     * Returns the ID.
     * @return
     */
    public Long getID() {
	return ID;
    }

    /**
     * Sets the ID to the parameter value.
     * @param ID
     */
    public void setID(Long ID) {
	this.ID = ID;
    }

    /**
     * Returns the name.
     * @return
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name to the parameter value.
     * @param name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Returns the description.
     * @return
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets the description to the parameter value.
     * @param description
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Returns the price.
     * @return
     */
    public BigDecimal getPrice() {
	return price;
    }

    /**
     * Sets the price to the parameter value.
     * @param price
     */
    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    /**
     * Returns the datetime.
     * @return
     */
    public Date getDatetime() {
	return datetime;
    }

    /**
     * Sets the datetime to the parameter value.
     * @param datetime
     */
    public void setDatetime(Date datetime) {
	this.datetime = datetime;
    }  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.price);
        hash = 23 * hash + Objects.hashCode(this.datetime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.datetime, other.datetime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "ID=" + ID + ", name=" + name + ", description=" + description + ", price=" + price + ", datetime=" + datetime + '}';
    }
    
    
}
