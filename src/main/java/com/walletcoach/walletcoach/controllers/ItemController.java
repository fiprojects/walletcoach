package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
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
    
    public List<Item> getAll() throws XQException {
        List<Item> items = new ArrayList<>();

        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("itemList"));
        expression.bindInt(new QName("monthParameter"), 2, null);
        
        XQResultSequence result = expression.executeQuery();
        while(result.next()) {
            DOMTools domTools = new DOMTools((Element)result.getObject());
            Item item = new Item();
            item.setID(domTools.getLong("id", true));
            item.setDescription(domTools.getString("description"));
            //item.setPrice(domTools.get("id", true));
            
            items.add(item);
        }
        
        return items;
    } 
}
