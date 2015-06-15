package com.walletcoach.walletcoach.tools;

import java.io.IOException;
import java.util.Calendar;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;

public class ItemsQueryBuilder implements QueryBuilder {
    private boolean displayIncome = false;
    private int month;
    private int year;
    private Long categoryId = null;
    private Long subjectId = null;
    
    public ItemsQueryBuilder() {
        Calendar calendar = Calendar.getInstance();
        filterPeriod(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }
    
    /**
     * Displays expenses.
     */
    public void displayExpenses() {
        displayIncome = false;
    }
    
    /**
     * Displays income.
     */
    public void displayIncome() {
        displayIncome = true;
    }
    
    /**
     * Filters the results based on the given month and year.
     * 
     * @param month
     * @param year 
     */
    public void filterPeriod(int month, int year) {
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month.");
        }
        
        this.month = month;
        this.year = year;
    }
    
    /**
     * Filters the results based on the given Category.
     * 
     * @param categoryId 
     */
    public void filterCategory(long categoryId) {
        this.categoryId = categoryId;
    }
    
    /**
     * Filters the results based on the given Subject.
     * 
     * @param subjectId 
     */
    public void filterSubject(long subjectId) {
        this.subjectId = subjectId;
    }
    
    /**
     * Prepares an XQuery expression based on the class variables values.
     * 
     * @param xml
     * @return XQPreparedExpression
     * @throws XQException
     * @throws IOException 
     */
    @Override
    public XQPreparedExpression getQuery(XQConnection xml) throws XQException, IOException {
        XQPreparedExpression expression = xml.prepareExpression(getExpression());
        expression.bindInt(new QName("p_month"), month, null);
        expression.bindInt(new QName("p_year"), year, null);
        
        if(categoryId != null) {
            expression.bindLong(new QName("p_category"), categoryId, null);
        }
        
        if(subjectId != null) {
            expression.bindLong(new QName("p_subject"), subjectId, null);
        }
        
        return expression;
    }
    
    /**
     * Returns a prepared expression based on the values of class variables.
     * 
     * @return String 
     * @throws IOException 
     */
    public String getExpression() throws IOException {
        String expression = XMLConnection.getQueryString("itemList");
        
        StringBuilder declarations = new StringBuilder();
        StringBuilder where = new StringBuilder();
        
        declarations.append(external("p_month"));
        declarations.append(external("p_year"));
        where.append("where $month = $p_month and $year = $p_year");        
        
        if(displayIncome) {
            where.append(" and $item/price >= 0");
        } else {
            where.append(" and $item/price <= 0");
        }
        
        if(categoryId != null) {
            declarations.append(external("p_category"));
            where.append(" and $item/category-id = $p_category");
        }
        
        if(subjectId != null) {
            declarations.append(external("p_subject"));
            where.append(" and $item/company-id = $p_subject");
        }
        
        expression = expression.replace("%declarations%", declarations.toString());
        expression = expression.replace("%where%", where.toString());        
        
        return expression;
    }
    
    /**
     * Helping method that creates a String 
     * declaring the name as an external integer.
     * 
     * @param name
     * @return String
     */
    private String external(String name) {
        return "declare variable $" + name + " as xs:integer external;" + System.lineSeparator();
    }
}
