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
 * This class represents a controller for Subjects.
 * 
 * @author fajlo
 */
public class SubjectController {
    
    /**
     * Returns the list of all Subjects recorded in the application.
     * 
     * @return List<Subjects>
     * @throws Exception 
     */
    public List<Subject> getAll() throws Exception {
        List<Subject> subjects = new ArrayList<>();

        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "subjects");
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("subjectList"));
        XQResultSequence sequence = expression.executeQuery();
        XQSequence result = xml.createSequence(sequence);
        while(result.next()) {
            Element element = (Element)result.getObject();
            subjects.add(parseItem(element));
        }
        XMLConnection.closeDb(xml);
        xml.close();
        
        return subjects;
    }
    
    /**
     * Returns the Subject of a given Item defined by its id.
     * 
     * @param id
     * @return Subject
     * @throws XQException 
     */
    public Subject getItem(Long id) throws XQException {
        Subject item = null;
        
        XQConnection xml = XMLConnection.getConnection();
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
        xml.close();   
        
        return item;
    }
    /**
     * Adds the Subject if not existent.
     * Edits a specific Subject if existing.
     * 
     * @param subject
     * @throws XQException 
     */
    public void edit(Subject subject) throws XQException {
        XQConnection xml = XMLConnection.getConnection();
        XMLConnection.openDb(xml, "subjects");
        
        XQPreparedExpression expression;
        if(subject.getID() == null) {
            expression = xml.prepareExpression(XMLConnection.getQuery("subjectInsert"));
        } else {
            expression = xml.prepareExpression(XMLConnection.getQuery("subjectUpdate"));
            expression.bindLong(new QName("id"), subject.getID(), null);
        }
        
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
        xml.close();
    }
    /**
     * Deletes a specific Subject.
     * 
     * @param subject
     * @throws XQException 
     */
    public void delete(Subject subject) throws XQException {
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("subjectDelete"));
        expression.bindLong(new QName("id"), subject.getID(), null);
        expression.executeQuery();
        
        XMLConnection.openDb(xml, "subjects");
        XMLConnection.save(xml, "subjects");
        XMLConnection.closeDb(xml);
        
        XMLConnection.openDb(xml, "items");
        XMLConnection.save(xml, "items");
        XMLConnection.closeDb(xml);
        
        xml.close();
    }
    
    /**
     * Gets a Subject from Ares.
     * Takes the IC and executes a query using it as parameter.
     * Takes the object from the result 
     * and parses the information into a subject.
     * 
     * @param ic
     * @return Subject
     * @throws XQException 
     */
    public Subject getFromAres(String ic) throws XQException {
        XQConnection xml = XMLConnection.getConnection();
        XQPreparedExpression expression = xml.prepareExpression(XMLConnection.getQuery("ares"));
        expression.bindString(new QName("ic"), "http://wwwinfo.mfcr.cz/cgi-bin/ares/darv_bas.cgi?ico=" + ic, null);
        XQResultSequence result = expression.executeQuery();
        
        Subject subject = null;
        if(result.next()) {
            Element element = (Element)result.getObject();
            DOMTools domTools = new DOMTools(element);
            
            subject = new Subject();
            subject.setName(domTools.getString("name"));
            subject.setStreet(domTools.getString("street"));
            subject.setNumber(domTools.getString("number"));
            subject.setCity(domTools.getString("city"));
        }
        
        xml.close();
        
        return subject;
    }
    /**
     * Item parser, 
     * takes an element and returns a Subject containing parsed information.
     * 
     * @param element
     * @return
     * @throws XQException 
     */
    public Subject parseItem(Element element) throws XQException {
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