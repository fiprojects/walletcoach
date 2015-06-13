package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.QueryBuilder;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ItemController {
    private final CategoryController categoryController;
    private final SubjectController subjectController;
    
    public ItemController(CategoryController categoryController, SubjectController subjectController) {
        this.categoryController = categoryController;
        this.subjectController = subjectController;
    }
    
    public List<Item> getAll() throws Exception {
        List<Item> items = new ArrayList<>();
        
        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "items");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("itemListAll"));
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
    
    public List<Item> getFiltered(QueryBuilder query) throws Exception {
        List<Item> items = new ArrayList<>();

        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "items");
        XQResultSequence sequence = query.getQuery(xml).executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            items.add(parseItem(element));
        }
        XMLConnection.closeDb(xml);
        xml.close();
        
        return items;
    }
    
    public void add(Item item) throws XQException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = item.getDatetime().getTime();
        
        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "items");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("itemInsert"));
        expression.bindString(new QName("description"), item.getDescription(), null);
        expression.bindObject(new QName("price"), item.getPrice(), null);
        expression.bindString(new QName("datetime"), format.format(date), null);
        expression.bindLong(new QName("categoryId"), item.getCategory().getID(), null);
        expression.bindLong(new QName("subjectId"), item.getSubject().getID(), null);
        expression.executeQuery();
        
        XMLConnection.save(xml, "items");
        XMLConnection.closeDb(xml);
        xml.close();
    }
    
    private Item parseItem(Element element) throws XQException {
        Element data = (Element)element.getElementsByTagName("data").item(0);
        Element category = (Element)element.getElementsByTagName("category").item(0);
        Element subject = (Element)element.getElementsByTagName("subject").item(0);
        
        DOMTools domTools = new DOMTools(data);
        Item item = new Item();
        item.setID(domTools.getLong("id", true));
        item.setDatetime(domTools.getDatetime("datetime"));
        item.setPrice(domTools.getBigDecimal("price"));
        item.setDescription(domTools.getString("description"));
        
        item.setCategory(categoryController.parseItem(category));
        //if(subject != null) subjectController.parseItem((Element)subject);
        
        return item;
    }
}
