package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQSequence;
import org.w3c.dom.Element;

/**
 * This class represents a controller for Categories.
 * 
 * @author xle
 */
public class CategoryController {
    /**
     * Returns a list of all Categories recorded in the application.
     * 
     * @return
     * @throws XQException 
     */
    public List<Category> getAll() throws XQException {
        List<Category> items = new ArrayList<>();
        
        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "categories");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("categoryList"));
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();            
            items.add(parseItem(element));
        }
        XMLConnection.closeDb(xml);
        xml.close();
        
        return items;
    } 
    
    /**
     * Returns the Category of a selected Item, given by its id.
     * 
     * @param id
     * @return Category
     * @throws XQException 
     */
    public Category getItem(Long id) throws XQException {
        Category item = null;
        
        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "categories");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("category"));
        expression.bindLong(new QName("id"), id, null);
        
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        if(result.next()) {
            Element element = (Element)result.getObject();
            item = parseItem(element);
        }
        XMLConnection.closeDb(xml);
        xml.close();
        
        return item;
    }
    
    /**
     * Enables editing of a Category by overriding the category.
     * by the parameter.
     * 
     * @param category
     * @throws XQException 
     */
    public void edit(Category category) throws XQException {
        String colorString = colorToHex(category.getColor());
        
        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "categories");
        
        XQPreparedExpression expression;
        if(category.getID() == null) {
            expression = xml.prepareExpression(XMLConnection.getQuery("categoryInsert"));
        } else {
            expression = xml.prepareExpression(XMLConnection.getQuery("categoryUpdate"));
            expression.bindLong(new QName("id"), category.getID(), null);
        }
        
        expression.bindString(new QName("name"), category.getName(), null);
        expression.bindString(new QName("color"), colorString, null);
        expression.executeQuery();
        
        XMLConnection.save(xml, "categories");
        XMLConnection.closeDb(xml);
        xml.close();
    }
    
    /**
     * Deletes a given Category.
     * 
     * @param category
     * @throws XQException 
     */
    public void delete(Category category) throws XQException {
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("categoryDelete"));
        expression.bindLong(new QName("id"), category.getID(), null);
        expression.executeQuery();
        
        XMLConnection.openDb(xml, "categories");
        XMLConnection.save(xml, "categories");
        XMLConnection.closeDb(xml);
        
        XMLConnection.openDb(xml, "items");
        XMLConnection.save(xml, "items");
        XMLConnection.closeDb(xml);
        
        xml.close();
    }
    
    /**
     * Item parser, returns a Category item filled with attributes.
     * 
     * from the input element.
     * @param element
     * @return Category
     */
    public Category parseItem(Element element) {
        DOMTools domTools = new DOMTools(element);
        Category item = new Category();
        item.setID(domTools.getLong("id", true));
        item.setName(domTools.getString("name"));
        item.setColor(domTools.getColor("color"));
        
        return item;
    }
    
    /**
     * Transforms a color into hexadecimal format.
     * 
     * @param color
     * @return String
     */
    public String colorToHex(Color color) {
        if(color == null) {
            return "ffffff";
        }
        
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
