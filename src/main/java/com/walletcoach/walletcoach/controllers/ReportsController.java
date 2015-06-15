package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.tools.XMLConnection;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URISyntaxException;
import javax.xml.namespace.QName;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;

/**
 * This class represents a controller for reports.
 * 
 * @author Michael
 */
public class ReportsController {
    
    /**
     * Returns a String path for output of a monthly report
     * based on the choden month of a year.
     * 
     * @param month
     * @param year
     * @return String
     * @throws XQException
     * @throws FileNotFoundException
     * @throws URISyntaxException 
     */
    public String monthReport(int month, int year) throws XQException, FileNotFoundException, URISyntaxException {
        String output = "reports/xml/month_" + month + "_" + year + ".xml";
        
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("monthReport"));
        expression.bindInt(new QName("globalMonth"), month, null);
        expression.bindObject(new QName("globalYear"), year, null);
        expression.executeQuery().writeSequence(
            new FileOutputStream(output), null);
        
        xml.close();
        
        return output;
    }
    
    /**
     * Returns a String containing the path for a monthly report 
     * based on the chosen month of a year.
     * Transforms the input based on the template to output.
     * 
     * @param month
     * @param year
     * @return String
     * @throws XQException
     * @throws FileNotFoundException
     * @throws URISyntaxException 
     */
    public String monthReportHtml(int month, int year) throws XQException, FileNotFoundException, URISyntaxException {       
        String template = "reports/xsl/monthReport.xsl";
        String input = monthReport(month, year);
        String output = "reports/html/month_" + month + "_" + year + ".html";
        
        transform(input, output, template);
        return output;
    }
    
    /**
     * Returns the path for a yearly report based on the specified year.
     * 
     * @param year
     * @return String
     * @throws XQException
     * @throws FileNotFoundException
     * @throws URISyntaxException 
     */
    public String yearReport(int year) throws XQException, FileNotFoundException, URISyntaxException {
        String path = "reports/xml/year_" + year + ".xml";
        
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("yearReport"));
        expression.bindObject(new QName("globalYear"), year, null);
        expression.executeQuery().writeSequence(
            new FileOutputStream(path), null);
        
        xml.close();
        return path;
    }
    
    /**
     * Returns the path for a yearly report based on the specified year.
     * Transform the input based on the template to output.
     * 
     * @param year
     * @return String
     * @throws XQException
     * @throws FileNotFoundException
     * @throws URISyntaxException 
     */
    public String yearReportHtml(int year) throws XQException, FileNotFoundException, URISyntaxException {
        String template = "reports/xsl/yearReport.xsl";
        String input = yearReport(year);;
        String output = "reports/html/year_" + year + ".html";
        
        transform(input, output, template);
        return output;
    }
    
    /**
     * Returns the path for a yearly report in Latex based on the specified year.
     * Transform the input based on the template to output.     * 
     * 
     * @param year
     * @return
     * @throws XQException
     * @throws FileNotFoundException
     * @throws URISyntaxException 
     */
    public String yearReportLatex(int year) throws XQException, FileNotFoundException, URISyntaxException {
        String template = "reports/xsl/yearReportLatex.xsl";
        String input = yearReport(year);;
        String output = "reports/latex/year_" + year + ".tex";
        
        transform(input, output, template);
        return output;
    }
    
    /**
     * Transforms input based on the template to output.
     * 
     * @param input
     * @param output
     * @param template
     * @throws XQException
     * @throws FileNotFoundException 
     */
    private void transform(String input, String output, String template) throws XQException, FileNotFoundException {
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(
            "xslt:transform-text('" + input + "', '" + template + "')");
        expression.executeQuery().writeSequence(
            new FileOutputStream(output), null);
        
        xml.close();
    }
}
