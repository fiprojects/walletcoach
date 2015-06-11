package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Item;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xquery.XQConnection;

/**
 *
 * @author xle
 */
public class ItemController {
    private XQConnection xml;
    
    public ItemController(XQConnection xml) {
        this.xml = xml;
    }
    
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        
        return items;
    } 
}
