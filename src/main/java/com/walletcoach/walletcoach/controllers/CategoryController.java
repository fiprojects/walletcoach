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
        List<Category> items = new ArrayList<>();
        
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("categoryList"));
        XQResultSequence result = expression.executeQuery();
        while(result.next()) {
            Element element = (Element)result.getObject();            
            items.add(parseItem(element));
        }
        
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
    
    private Category parseItem(Element element) {
        DOMTools domTools = new DOMTools(element);
        Category item = new Category();
        item.setID(domTools.getLong("id", true));
        item.setName(domTools.getString("name"));
        item.setColor(domTools.getColor("color"));
        
        return item;
    }
}
