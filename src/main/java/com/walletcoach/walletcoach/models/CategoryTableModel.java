package com.walletcoach.walletcoach.models;

import com.walletcoach.walletcoach.controllers.CategoryController;
import com.walletcoach.walletcoach.entities.Category;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.swing.table.AbstractTableModel;
import javax.xml.xquery.XQException;

public class CategoryTableModel extends AbstractTableModel {    
    private CategoryController controller;
    protected List<Category> items = new ArrayList<>();

    public CategoryTableModel(CategoryController controller) {
        this.controller = controller;
        loadData();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getID();
            case 1:
                return item.getName();
            case 2:
                return item.getColor();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "nazev";
            case 2:
                return "barva";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void loadData() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws XQException {
		items = controller.getAll();
                return null;
            }

            protected void done() {
                fireTableDataChanged();
            }
        }.execute();
    }
}