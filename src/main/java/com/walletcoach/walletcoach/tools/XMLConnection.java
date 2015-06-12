package com.walletcoach.walletcoach.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQConstants;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQStaticContext;
import net.xqj.basex.local.BaseXXQDataSource;
import org.apache.commons.io.IOUtils;

/**
 * BaseX Connection
 * @author Michael Le <lemichael@mail.muni.cz>
 */
public class XMLConnection {
    /**
     * Get BaseX/XQJ connection
     * @return BaseX/XQJ connection
     * @throws XQException if error occurred
     */
    public static XQConnection getConnection() throws XQException {
        XQDataSource dataSource = new BaseXXQDataSource();
        XQConnection connection = dataSource.getConnection();
        
        connection.createExpression().executeCommand("SET writeback true");
        
        return connection;
    }
    
    /**
     * Get prepared query
     * @param file Query file
     * @return Query stream
     */
    public static InputStream getQuery(String file) {
        return XMLConnection.class.getClassLoader().getResourceAsStream(file + ".xq");
    }
    
    /**
     * Get prepared query as string
     * @param file Query file
     * @return Query string
     * @throws java.io.IOException if query file not found
     */
    public static String getQueryString(String file) throws IOException {
        InputStream stream = getQuery(file);
        String string = IOUtils.toString(stream);
        IOUtils.closeQuietly(stream);
        
        return string;
    }
}
