package com.walletcoach.walletcoach.tools;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.xml.bind.DatatypeConverter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class 
 * 
 * @author xle
 */
public class DOMTools {
    private final Element element;    
    
    public DOMTools(Element element) {
        this.element = element;
    }
    
    /**
     * Overloads getString(String, boolean) method.
     * 
     * @param name
     * @return String
     */
    public String getString(String name) {
        return getString(name, false);
    }
    
    /**
     * Returns either the name node
     * or the text content of the name node.
     * 
     * @param name
     * @param isAttribute
     * @return 
     */
    public String getString(String name, boolean isAttribute) {
        if(isAttribute) {
            return getAttribute(name);
        } else {
            return getNode(name).getTextContent();
        }
    }
    
    /**
     * Overloads the getInt(String, boolean) method.
     * 
     * @param name
     * @return int
     */
    public int getInt(String name) {
        return getInt(name, false);
    }
    
    /**
     * Returns either the name node
     * or the text content of the name node.
     * 
     * @param name
     * @param isAttribute
     * @return 
     */
    public int getInt(String name, boolean isAttribute) {
        return Integer.parseInt(getString(name, isAttribute));
    }
    
    /**
     * Overloads the getLong(String, boolean) method.
     * 
     * @param name
     * @return long
     */
    public long getLong(String name) {
        return getLong(name, false);
    }
    
    /**
     * Calls the getString method.
     * Returns either the node
     * or the text content of the node parsed to long.
     * 
     * @param name
     * @param isAttribute
     * @return long
     */
    public long getLong(String name, boolean isAttribute) {
        return Long.parseLong(getString(name, isAttribute));
    }
    /**
     * Overloads the getBigDecimal(String, boolean) method.
     * 
     * @param name
     * @return BigDecimal
     */
    public BigDecimal getBigDecimal(String name) {
        return getBigDecimal(name, false);
    }
    
    /**
     * Calls the getString method.
     * Returns either the node
     * or the text content of the node parsed to BigDecimal.
     * 
     * @param name
     * @param isAttribute
     * @return 
     */
    public BigDecimal getBigDecimal(String name, boolean isAttribute) {      
        return new BigDecimal(getString(name, isAttribute));
    }
    
    /**
     * Overloads the getDateTime(String, boolean) method.
     * 
     * @param name
     * @return 
     */
    public Calendar getDatetime(String name) {
        return getDatetime(name, false);
    }
    
    /**
     * Calls the getString method.
     * Returns either the node
     * or the text content of the node parsed to DateTime.
     * 
     * @param name
     * @param isAttribute
     * @return 
     */
    public Calendar getDatetime(String name, boolean isAttribute) {
        return DatatypeConverter.parseDateTime(getString(name, isAttribute));
    }
    
    /**
     * Overloads the getColor(String, boolean) method.
     * 
     * @param name
     * @return Color
     */
    public Color getColor(String name) {
        return getColor(name, false);
    }
    
    /**
     * If the color is not an attribute, decodes the String to Color.
     * Otherwise, throws an exception.
     * 
     * @param name
     * @param isAttribute
     * @return Color
     */
    public Color getColor(String name, boolean isAttribute) {
        Color color = new Color(0xFFFFFF);
        try {
            color = Color.decode(getString(name, isAttribute));
        } catch(Exception e) {}
        
        return color;
    }
    
    /**
     * Returns the node specified by a given name.
     * 
     * @param name
     * @return Node
     * @throws IllegalArgumentException 
     */
    private Node getNode(String name) throws IllegalArgumentException {
        NodeList nodes = element.getElementsByTagName(name);
        if(nodes.getLength() != 1) { 
            throw new IllegalArgumentException(); // TODO: edit
        }
        
        return nodes.item(0);
    }
    
    /**
     * Returns the attribute of an element.
     * 
     * @param name
     * @return 
     */
    private String getAttribute(String name) {
        return element.getAttribute(name);
    }
}
