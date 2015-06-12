package com.walletcoach.walletcoach;

import com.walletcoach.walletcoach.controllers.ItemController;
import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.tools.I18n;
import com.walletcoach.walletcoach.tools.ItemsQueryBuilder;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.io.IOException;

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
        
        ItemController ctl = new ItemController(XMLConnection.getConnection());
        for(Item i : ctl.getAll()) {
            System.out.println(i);
        }
    }
}