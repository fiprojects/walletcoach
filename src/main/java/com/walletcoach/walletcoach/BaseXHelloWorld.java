package com.walletcoach.walletcoach;

import com.walletcoach.walletcoach.tools.XMLConnection;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQResultSequence;
/**
 * BaseX Demo
 * @author Michael Le
 */
public class BaseXHelloWorld {
    public static void main(String[] args) throws XQException {
        XQConnection xml = XMLConnection.getConnection();
        
        XQResultSequence result = xml.createExpression().executeQuery("doc('data/fruits.xml')//fruit/name/text()");
        while(result.next()) {
            System.out.println(result.getNode().getNodeValue());
        }
        xml.close();
    }
}