package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Tabtime extends AbstractTableModel {
    ArrayList<time> person;

    String[] columns ={"Time"};
    public Tabtime(){
        person = new ArrayList<>();
        //person.add(new Person("John ", "Paul", "Janitor", 20000));
    }

    public void adding(time student){
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

    public void remove(int index) {
        person.remove(index);
        fireTableDataChanged();
    }

    public time get(int index) {

        return person.get(index);
    }

    public void update(int index, time updatedPerson) {
        if(index >= 0 && index < person.size()) {
            person.set(index, updatedPerson);
            fireTableDataChanged();
        }
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        time po = person.get(rowIndex);
        return po.getTime();


    }
}