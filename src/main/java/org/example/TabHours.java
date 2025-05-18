package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TabHours extends AbstractTableModel {
    ArrayList<HoursWork> person;

    String[] columns = {"Date", "Time", "Status"};

    public TabHours() {
        person = new ArrayList<>();
    }

    public void adding(HoursWork student) {
        person.add(student);
        fireTableDataChanged();
    }

    public String getColumnName(int column) {
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

    public int getTotalAttendanceCount() {
        int count = 0;
        for (HoursWork hw : person) {
            String status = hw.getAttendanceStatus();
            if (status.equalsIgnoreCase("Present") ||
                    status.equalsIgnoreCase("Absent") ||
                    status.equalsIgnoreCase("Leave")) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HoursWork po = person.get(rowIndex);

        switch (columnIndex) {
            case 0: return po.getDate();
            case 1: return po.getHour();
            case 2: return po.getAttendanceStatus();
            default: return null;
        }
    }
}
