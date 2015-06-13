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
 *
 * @author Michael
 */
public class ReportsController {
    public void monthReport(int month, int year) throws XQException, FileNotFoundException, URISyntaxException {
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("monthReport"));
        expression.bindInt(new QName("globalMonth"), month, null);
        expression.bindObject(new QName("globalYear"), year, null);
        expression.executeQuery().writeSequence(
            new FileOutputStream("reports/month_" + month + "_" + year + ".xml"), null);
        
        xml.close();
        monthReportHtml(month, year);
    }
    
    public void monthReportHtml(int month, int year) throws XQException, FileNotFoundException, URISyntaxException {
        String template = "reports/xsl/monthReport.xsl";
        String input = "reports/month_" + month + "_" + year + ".xml";
        String output = "reports/month_" + month + "_" + year + ".html";
        
        transform(input, output, template);
    }
    
    public void yearReport(int year) throws XQException, FileNotFoundException, URISyntaxException {
        String path = "reports/year_" + year + ".xml";
        
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("yearReport"));
        expression.bindObject(new QName("globalYear"), year, null);
        expression.executeQuery().writeSequence(
            new FileOutputStream(path), null);
        
        xml.close();
        yearReportHtml(year);
    }
    
    public void yearReportHtml(int year) throws XQException, FileNotFoundException, URISyntaxException {
        String template = "reports/xsl/yearReport.xsl";
        String input = "reports/year_" + year + ".xml";
        String output = "reports/year_" + year + ".html";
        
        transform(input, output, template);
    }
    
    private void transform(String input, String output, String template) throws XQException, FileNotFoundException {
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(
            "xslt:transform-text('" + input + "', '" + template + "')");
        expression.executeQuery().writeSequence(
            new FileOutputStream(output), null);
        
        xml.close();
    }
}
