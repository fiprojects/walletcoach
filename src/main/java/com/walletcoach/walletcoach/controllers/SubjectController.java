package com.walletcoach.walletcoach.controllers;

import com.walletcoach.walletcoach.entities.Subject;
import com.walletcoach.walletcoach.tools.DOMTools;
import com.walletcoach.walletcoach.tools.XMLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
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

        XMLConnection.openDb(xml, "subjects");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("subjectList"));
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            subjects.add(parseItem(element));
        }
        XMLConnection.closeDb(xml);
        
        return subjects;
    }
    
    public Subject getItem(Long id) throws XQException {
        Subject item = null;
        
        XMLConnection.openDb(xml, "subjects");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("subject"));
        expression.bindLong(new QName("id"), id, null);
        
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        if(result.next()) {
            Element element = (Element)result.getObject();
            item = parseItem(element);
        }
        XMLConnection.closeDb(xml);   
        
        return item;
    }
    
    public void add(Subject subject) throws XQException {
        XMLConnection.openDb(xml, "subjects");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("subjectInsert"));
        expression.bindString(new QName("ic"), subject.getIc(), null);
        expression.bindString(new QName("name"), subject.getName(), null);
        expression.bindString(new QName("street"), subject.getStreet(), null);
        expression.bindString(new QName("number"), subject.getNumber(), null);
        expression.bindString(new QName("city"), subject.getCity(), null);
        expression.bindString(new QName("country"), subject.getCountry(), null);
        expression.bindString(new QName("description"), subject.getDescription(), null);
        expression.executeQuery();
        
        XMLConnection.save(xml, "subjects");
        XMLConnection.closeDb(xml);
    }
    
    private Subject parseItem(Element element) throws XQException {
        DOMTools domTools = new DOMTools(element);
        Subject subject = new Subject();
        subject.setID(domTools.getLong("id", true));
        subject.setIc(domTools.getString("ic"));
        subject.setName(domTools.getString("name"));
        subject.setStreet(domTools.getString("street"));
        subject.setNumber(domTools.getString("number"));
        subject.setCity(domTools.getString("city"));
        subject.setCountry(domTools.getString("country"));
        subject.setDescription(domTools.getString("description"));
        
        return subject;
    }
}
