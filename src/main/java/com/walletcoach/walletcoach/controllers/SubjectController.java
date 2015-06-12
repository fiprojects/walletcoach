package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Item;
import com.walletcoach.walletcoach.entities.Subject;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.QueryBuilder;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQSequence;
import org.w3c.dom.Element;

/**
 *
 * @author fajlo
 */
public class SubjectController {
    private XQConnection xml;
    
    public SubjectController(XQConnection xml) {
        this.xml = xml;
    }
    
    public List<Subject> getAll() throws Exception {
        List<Subject> subjects = new ArrayList<>();

        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("subjectListAll"));
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            subjects.add(parseItem(element));
        }
        
        return subjects;
    }
    
    public List<Subject> getFiltered(QueryBuilder query) throws Exception {
        List<Subject> subjects = new ArrayList<>();

        XQResultSequence sequence = query.getQuery(xml).executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            subjects.add(parseItem(element));
        }
        
        return subjects;
    }
    
    private Subject parseItem(Element element) throws XQException {
        DOMTools domTools = new DOMTools(element);
        Subject subject = new Subject();
        subject.setID(domTools.getLong("id", true));
        subject.setIc(domTools.getString("ic"));
        subject.setStreet(domTools.getString("street"));
        subject.setNumber(domTools.getString("number"));
        subject.setCity(domTools.getString("city"));
        subject.setCountry(domTools.getString("country"));
        subject.setDescription(domTools.getString("description"));
        
        return subject;
    }
}
