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