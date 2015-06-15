package com.walletcoach.walletcoach.tools;

/**
 * This class is used for Combo Boxes on forms.
 * 
 * @author Michael
 */
public class JComboBoxItem {
    private String name;
    private Object item;
    
    public JComboBoxItem(String name, Object item) {
        this.name = name;
        this.item = item;
    }    
    
    /**
     * Returns the value of the name parameter.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value to the name parameter.
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Returns the value of the  parameter.
     * 
     * @return Object
     */
    public Object getItem() {
        return item;
    }

    /**
     * Sets the value to the item parameter.
     * 
     * @param item 
     */
    public void setItem(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return name;
    }    
}
