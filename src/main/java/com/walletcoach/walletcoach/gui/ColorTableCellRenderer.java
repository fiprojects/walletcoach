package com.walletcoach.walletcoach.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTableCellRenderer extends JLabel implements TableCellRenderer {
    private Color color;
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int rowIndex, int vColIndex) {
        color = (Color)value;
        return this;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
