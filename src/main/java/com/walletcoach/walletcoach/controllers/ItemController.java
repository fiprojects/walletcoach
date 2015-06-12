package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.QueryBuilder;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.util.ArrayList;
import java.util.List;
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
public class ItemController {
    private XQConnection xml;
    
    public ItemController(XQConnection xml) {
        this.xml = xml;
    }
    
    public List<Item> getAll() throws Exception {
        List<Item> items = new ArrayList<>();

        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("itemListAll"));
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            items.add(parseItem(element));
        }
        
        return items;
    }
    
    public List<Item> getFiltered(QueryBuilder query) throws Exception {
        List<Item> items = new ArrayList<>();

        XQResultSequence sequence = query.getQuery(xml).executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            items.add(parseItem(element));
        }
        
        return items;
    }
    
    private Item parseItem(Element element) throws XQException {
        DOMTools domTools = new DOMTools(element);
        Item item = new Item();
        item.setID(domTools.getLong("id", true));
        item.setDatetime(domTools.getDatetime("datetime"));
        item.setPrice(domTools.getBigDecimal("price"));
        item.setDescription(domTools.getString("description"));
        
        Long categoryId = domTools.getLong("category-id");
        CategoryController categoryController = new CategoryController(xml);
        item.setCategory(categoryController.getItem(categoryId));
        
        Long subjectId = domTools.getLong("company-id");
        item.setSubject(null);
        
        return item;
    }
}
