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
 *
 * @author xle
 */
public class CategoryController {
    private XQConnection xml;
    
    public CategoryController(XQConnection xml) {
        this.xml = xml;
    }
    
    public List<Category> getAll() throws XQException {
        XQConnection xml = XMLConnection.getConnection();
        
        List<Category> items = new ArrayList<>();
        
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("categoryList"));
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();            
            items.add(parseItem(element));
        }
        
        xml.close();
        
        return items;
    } 
    
    public Category getItem(Long id) throws XQException {
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("category"));
        expression.bindLong(new QName("id"), id, null);
        
        XQResultSequence result = expression.executeQuery();
        if(result.next()) {
            Element element = (Element)result.getObject();
            return parseItem(element);
        }
        
        return null;
    }
    
    public void add(Category category) throws XQException {
        Color color = category.getColor();
        String colorString = String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
        
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("categoryInsert"));
        expression.bindString(new QName("name"), category.getName(), null);
        expression.bindString(new QName("color"), colorString, null);
        expression.executeQuery();
        expression.close();
    }
    
    private Category parseItem(Element element) {
        DOMTools domTools = new DOMTools(element);
        Category item = new Category();
        item.setID(domTools.getLong("id", true));
        item.setName(domTools.getString("name"));
        item.setColor(domTools.getColor("color"));
        
        return item;
    }
}
