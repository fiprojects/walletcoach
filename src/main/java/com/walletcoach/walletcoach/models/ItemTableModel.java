package com.walletcoach.walletcoach.models;

import com.walletcoach.walletcoach.controllers.ItemController;
import com.walletcoach.walletcoach.entities.Category;
import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.entities.Subject;
import com.walletcoach.walletcoach.tools.ItemsQueryBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.SwingWorker;

public class ItemTableModel extends ObjectTableModel {    
    private final ItemController controller;
    protected List<Item> items = new ArrayList<>();

    public ItemTableModel(ItemController controller, boolean displayIncome, Category category, Subject subject) {
        this.controller = controller;
        loadData(displayIncome, category, subject);
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
                return item.getPrice() + " CZK";
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
                return "Date";
            case 2:
                return "Price";
            case 3:
                return "Description";
            case 4:
                return "Category";
            case 5:
                return "Subject";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void loadData(boolean displayIncome, Category category, Subject subject) {
        final ItemsQueryBuilder query = new ItemsQueryBuilder();
        
        // Expenses/Incomes
        if(displayIncome) query.displayIncome();
        else query.displayExpenses();
        
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