package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Tablee extends AbstractTableModel {
    ArrayList<Person> person;

    String[] columns ={"Name" , "Position", "Basic Salary"};
    public Tablee(){
        person = new ArrayList<>();
        //person.add(new Person("John ", "Paul", "Janitor", 20000));
    }

    public void adding(Person student){
        person.add(student);
        fireTableDataChanged();
    }
    public void clear() {
        person.clear();
        fireTableDataChanged();
    }


    public String getColumnName(int column){
        return columns[column];
    }
    @Override
    public int getRowCount() {
        return person.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    public void remove(int index) {
        person.remove(index);
        fireTableDataChanged();
    }

    public Person get(int index) {

        return person.get(index);
    }


    public void update(int index, Person updatedPerson) {
        if(index >= 0 && index < person.size()) {
            person.set(index, updatedPerson);
            fireTableDataChanged();
        }
    }

    public void addRow(Person p) {
        person.add(p);
        fireTableRowsInserted(person.size() - 1, person.size() - 1);
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person po = person.get(rowIndex);

        if(columnIndex==0){
            return po.getFirst() + " " + po.getLast();
        }else if(columnIndex ==1) {
            return po.getPosition();
        }else{
            return po.getSalary();
        }

    }
}