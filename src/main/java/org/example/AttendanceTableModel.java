package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AttendanceTableModel extends AbstractTableModel {
    ArrayList<AttendanceKey> keys;

    String[] columns = {"Day", "Status"};

    public AttendanceTableModel(){
        keys = new ArrayList<>();
    }

    public void addKeys(AttendanceKey attendanceKey){
        keys.add(attendanceKey);
        this.fireTableDataChanged();
    }


    @Override
    public int getRowCount() {
        return keys.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column){
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AttendanceKey attendanceKey = keys.get(rowIndex);

        if (columnIndex == 0) {
            return attendanceKey.getDays();
        } else if (columnIndex == 1) {
            return attendanceKey.getStatus();
        } else {
            return null;
        }
    }

}
