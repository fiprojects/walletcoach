package com.walletcoach.walletcoach.tools;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQPreparedExpression;

public interface QueryBuilder {
    public XQPreparedExpression getQuery(XQConnection xml) throws Exception;
}
