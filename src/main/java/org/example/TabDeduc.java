package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabDeduc extends AbstractTableModel {
    ArrayList<Deduction> person;

    String[] columns ={"SSS" , "PhilHealth", "Pag-IBIG"};
    public TabDeduc(){

        person = new ArrayList<>();
    }

    public void adding(Deduction student){
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

    public Deduction get(int index) {
        return person.get(index);
    }


    public void update(int index, Deduction updatedDeduction) {
        if(index >= 0 && index < person.size()) {
            person.set(index, updatedDeduction);
            fireTableDataChanged();
        }
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Deduction po = person.get(rowIndex);

        if(columnIndex==0){
            return po.getSss();
        }else if(columnIndex == 1) {
            return po.getPhilhealth();
        }else{
            return po.getPagibig();
        }
    }
}
