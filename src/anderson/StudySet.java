/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package anderson;

import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author k money
 */


public class StudySet implements Serializable {

    private ArrayList<String> terms = new ArrayList();
    private ArrayList<String> definitions = new ArrayList();
    private String name;
    private double record;

    public StudySet(ArrayList<String> terms, ArrayList<String> definitions, String name) {
        this.terms = terms;
        this.definitions = definitions;
        this.name = name;
        
    }

    public ArrayList<String> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<String> terms) {
        this.terms = terms;
    }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }

    public void addTerm(String term) {
        terms.add(term);
    }

    public void addDefinition(String definition) {
        definitions.add(definition);
    }

    public void removeTerm(String term) {
        terms.remove(term);
    }

    public void removeDefinition(String definition) {
        definitions.remove(definition);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
    public void setRecord(double record) {
        this.record = record;
    }
    
    public double getRecord() {
        return record;
    }
}
