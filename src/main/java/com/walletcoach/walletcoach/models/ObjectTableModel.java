package com.walletcoach.walletcoach.models;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public abstract class ObjectTableModel extends AbstractTableModel {
    public abstract Object getRowObject(int rowIndex);
    
    public Object getSelectedObject(JTable table) {
        int rowIndex = table.getSelectedRow();
        if(rowIndex < 0 || rowIndex >= table.getRowCount()) {
            return null;
        }

        return getRowObject(rowIndex);
    }
}
