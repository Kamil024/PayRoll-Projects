package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabTotal extends AbstractTableModel {

    ArrayList<Total> person;

    String[] columns ={"Gross Pay" , "Net Pay", "Total Deduction"};
    public TabTotal(){
        person = new ArrayList<>();
    }

    public void adding(Total student){
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

    public Total get(int index) {
        return person.get(index);
    }


    public void update(int index, Total updatedTotal) {
        if(index >= 0 && index < person.size()) {
            person.set(index, updatedTotal);
            fireTableDataChanged();
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Total po = person.get(rowIndex);

        if(columnIndex==0){
            return po.getGrossPay();
        }else if(columnIndex == 1) {
            return po.getNetPay();
        }else{
            return po.getTotalDeduc();
        }
    }
}
