package com.walletcoach.walletcoach.entities;

import java.awt.Color;

/**
 * The class Category is a base class for various categories
 * of purchases that the user makes.
 * 
 * The class contains basic entities for storing information
 * such as the name and color identification of the category.
 * 
 * @author fajlo
 */

public class Category {
    private Long ID;
    private String name;
    private Color color;

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

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }
}
