package com.walletcoach.walletcoach.entities;

import java.awt.Color;
import java.util.Objects;

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
     * Returns the color.
     * @return
     */
    public Color getColor() {
	return color;
    }

    /**
     * Sets the color to the parameter value.
     * @param color
     */
    public void setColor(Color color) {
	this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.color);
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
        final Category other = (Category) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category{" + "ID=" + ID + ", name=" + name + ", color=" + color + '}';
    }
}
