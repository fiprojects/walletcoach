package com.walletcoach.walletcoach.tools;

/**
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return name;
    }    
}
