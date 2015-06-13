package com.walletcoach.walletcoach;

import com.walletcoach.walletcoach.controllers.CategoryController;
import com.walletcoach.walletcoach.controllers.ItemController;
import com.walletcoach.walletcoach.controllers.SubjectController;
import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.entities.Subject;
import com.walletcoach.walletcoach.gui.ReviewForm;
import com.walletcoach.walletcoach.tools.I18n;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.awt.Color;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;

/**
 * GitHub integration demo
 * @author Michael Le
 */
public class HelloWorld {
    public static void main(String[] args) throws IOException, Exception {
        System.out.print("Welcome to WalletCoach!");
        System.out.print("Test: Michael Le");
        System.out.println("\nBitches want my c... : Martin Lofaj");
        System.out.println("\nHello World : Samuel Adamik");
        System.out.println("\nI am also cool! Maros Gasparik");
        System.out.println("\nBut does it work? Maros Gasparik");
        
        System.out.println(I18n.get("category"));
        
        Subject sj = new Subject();
        sj.setID(1L);
        
        Category cat = new Category();
        cat.setID(1L);
        
        Item item = new Item();
        item.setDescription("Test");
        item.setPrice(new BigDecimal(108290));
        item.setDatetime(Calendar.getInstance());
        item.setSubject(sj);
        item.setCategory(cat);
        
        ItemController ic = new ItemController(new CategoryController(), new SubjectController());
        ic.add(item);
    }
}