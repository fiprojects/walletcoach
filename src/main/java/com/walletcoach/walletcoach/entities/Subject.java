package com.walletcoach.walletcoach.entities;

import java.util.Objects;

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
    private String street;
    private String number;
    private String city;
    private String country;
    private String description;

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
     * Returns the IC.
     * @return
     */
    public String getIc() {
	return ic;
    }

    /**
     * Sets the IC to the parameter value.
     * @param ic
     */
    public void setIc(String ic) {
	this.ic = ic;
    }

    /**
     * Returns the street.
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the street to the parameter value.
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Returns the street number.
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the street number to the parameter value.
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Returns the city.
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city to the parameter value.
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the country.
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country to the parameter value.
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.ic);
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
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.ic, other.ic)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Subject{" + "ID=" + ID + ", name=" + name + ", ic=" + ic + ", street=" + 
                street + ", number=" + number + ", city=" + city + ", country=" + country + 
                ", description=" + description + '}';
    }
    
    
}
