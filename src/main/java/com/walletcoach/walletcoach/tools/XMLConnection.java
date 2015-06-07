package com.walletcoach.walletcoach.tools;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import net.xqj.basex.local.BaseXXQDataSource;

/**
 * BaseX Connection
 * @author Michael Le <lemichael@mail.muni.cz>
 */
public class XMLConnection {
    public static XQConnection getConnection() throws XQException {
        XQDataSource dataSource = new BaseXXQDataSource();
        XQConnection connection = dataSource.getConnection();
        
        connection.createExpression().executeCommand("SET writeback true");
        
        return connection;
    }
}
