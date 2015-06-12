package com.walletcoach.walletcoach.tools;

import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author xle
 */
public class DOMTools {
    private Element element;    
    
    public DOMTools(Element element) {
        this.element = element;
    }
    
    public String getString(String name) {
        return getString(name, false);
    }
    
    public String getString(String name, boolean isAttribute) {
        if(isAttribute) {
            return getAttribute(name);
        } else {
            return getNode(name).getTextContent();
        }
    }
    
    public int getInt(String name) {
        return getInt(name, false);
    }
    
    public int getInt(String name, boolean isAttribute) {
        return Integer.parseInt(getString(name, isAttribute));
    }
    
    public long getLong(String name) {
        return getLong(name, false);
    }
    
    public long getLong(String name, boolean isAttribute) {
        return Long.parseLong(getString(name, isAttribute));
    }
    
    public BigDecimal getBigDecimal(String name) {
        return getBigDecimal(name, false);
    }
    
    public BigDecimal getBigDecimal(String name, boolean isAttribute) {      
        return new BigDecimal(getString(name, isAttribute));
    }
    
    public Calendar getDatetime(String name) {
        return getDatetime(name, false);
    }
    
    public Calendar getDatetime(String name, boolean isAttribute) {
        return DatatypeConverter.parseDateTime(getString(name, isAttribute));
    }
    
    public Color getColor(String name) {
        return getColor(name, false);
    }
    
    public Color getColor(String name, boolean isAttribute) {
        Color color = new Color(0xFFFFFF);
        try {
            color = Color.decode(getString(name, isAttribute));
        } catch(Exception e) {}
        
        return color;
    }
    
    private Node getNode(String name) throws IllegalArgumentException {
        NodeList nodes = element.getElementsByTagName(name);
        if(nodes.getLength() != 1) { 
            throw new IllegalArgumentException(); // TODO: edit
        }
        
        return nodes.item(0);
    }
    
    private String getAttribute(String name) {
        return element.getAttribute(name);
    }
}
