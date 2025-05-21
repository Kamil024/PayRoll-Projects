package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EmpoTable extends AbstractTableModel {

    ArrayList<Employee> person;

    String[] columns ={"ID Number" , "date", "Check in", "Check out"};
    public EmpoTable(){
        person = new ArrayList<>();

    }

    public void adding(Employee student){
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

    public Employee get(int index) {

        return person.get(index);
    }

    public void update(int index, Employee updatedPerson) {
        if(index >= 0 && index < person.size()) {
            person.set(index, updatedPerson);
            fireTableDataChanged();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("EmpoTable{\n");
        for (Employee e : person) {
            builder.append(e.toString()).append("\n");
        }
        builder.append("}");
        return builder.toString();
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee po = person.get(rowIndex);

        if (columnIndex == 0) {
            return po.getID();
        } else if (columnIndex == 1) {
            String formattedDate = String.format("%02d/%02d/%s",
                    Integer.parseInt(po.getMonth()),
                    Integer.parseInt(po.getDay()),
                    po.getYear());
            return formattedDate;
        } else if (columnIndex == 2) {
            return po.getCheckin();
        } else {
            return po.getCheckout();
        }
    }

}
