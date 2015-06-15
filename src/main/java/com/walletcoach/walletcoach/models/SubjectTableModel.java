package com.walletcoach.walletcoach.models;

import com.walletcoach.walletcoach.controllers.SubjectController;
import com.walletcoach.walletcoach.entities.Subject;
import com.walletcoach.walletcoach.tools.I18n;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;
import javax.xml.xquery.XQException;

public class SubjectTableModel extends ObjectTableModel {
    private final SubjectController controller;
    protected List<Subject> items = new ArrayList<>();

    public SubjectTableModel(SubjectController controller) {
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
        Subject item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getIc();
            case 1:
                return item.getName();
            case 2:
                return item.getDescription();
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
                return I18n.get("ic");
            case 1:
                return I18n.get("name");
            case 2:
                return I18n.get("description");
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
            protected Void doInBackground() throws XQException, Exception {
		items = controller.getAll();
                return null;
            }

            protected void done() {
                fireTableDataChanged();
            }
        }.execute();
    }
}