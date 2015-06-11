package com.walletcoach.walletcoach;

import com.walletcoach.walletcoach.controllers.CategoryController;
import com.walletcoach.walletcoach.controllers.ItemController;
import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.util.List;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
import org.w3c.dom.Element;

/**
 *
 * @author xle
 */
public class Application {
    public static void main(String[] args) throws XQException {
        // BaseX Connection
        XQConnection xml = null;
        try {
            xml = XMLConnection.getConnection();
        } catch(Exception e) {
            System.out.println("BaseX connection failed.");
            return;
        }
        
        ItemController itemController = new ItemController(xml);
        CategoryController categoryController = new CategoryController(xml);
        
        List<Item> items = itemController.getAll();
        for(Item item : items) {
            System.out.println(item);
        }
        
        try {
            xml.close();
        } catch(Exception e) {
            System.out.println("BaseX connection failed.");
            return;
        }
    }
}
