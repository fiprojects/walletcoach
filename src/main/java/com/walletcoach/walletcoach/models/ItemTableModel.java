package com.walletcoach.walletcoach.models;

import com.walletcoach.walletcoach.controllers.ItemController;
import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.entities.Subject;
import com.walletcoach.walletcoach.tools.I18n;
import com.walletcoach.walletcoach.tools.ItemsQueryBuilder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.SwingWorker;

public class ItemTableModel extends ObjectTableModel {    
    private final ItemController controller;
    protected List<Item> items = new ArrayList<>();

    public ItemTableModel(ItemController controller, boolean displayIncome, int month, int year, Category category, Subject subject) {
        this.controller = controller;
        loadData(displayIncome, month, year, category, subject);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getCategory().getColor();
            case 1:
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                Date date = item.getDatetime().getTime();
                return format.format(date);
            case 2:
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                df.setMinimumFractionDigits(2);
                df.setGroupingUsed(true);
                
                return df.format(item.getPrice()) + " CZK";
            case 3:
                return item.getDescription();
            case 4:
                return item.getCategory().getName();
            case 5:
                return item.getSubject().getName();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
    public Object getRowObject(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "";
            case 1:
                return I18n.get("date");
            case 2:
                return I18n.get("price");
            case 3:
                return I18n.get("description");
            case 4:
                return I18n.get("category");
            case 5:
                return I18n.get("subject");
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void loadData(boolean displayIncome, int month, int year, Category category, Subject subject) {
        final ItemsQueryBuilder query = new ItemsQueryBuilder();
        
        // Expenses/Incomes
        if(displayIncome) query.displayIncome();
        else query.displayExpenses();
        
        // Period
        query.filterPeriod(month, year);
        
        // Category
        if(category != null) {
            query.filterCategory(category.getID());
        }
        
        // Subject
        if(subject != null) {
            query.filterSubject(subject.getID());
        }
        
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                items = controller.getFiltered(query);
                return null;
            }

            @Override
            protected void done() {
                fireTableDataChanged();
            }
        }.execute();
    }
}