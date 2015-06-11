package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
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
        
        XQResultSequence result = xml.createExpression().executeQuery(XMLConnection.getQuery("categoryList"));
        while(result.next()) {
            Element item = (Element)result.getObject();

            DOMTools domTools = new DOMTools(item);
            Category category = new Category();
            category.setID(domTools.getLong("id", true));
            category.setName(domTools.getString("name"));
            category.setColor(domTools.getColor("color"));
            
            items.add(category);
        }
        
        return items;
    } 
}
