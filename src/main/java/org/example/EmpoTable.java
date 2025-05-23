package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmpoTable extends AbstractTableModel {

    ArrayList<Employee> person;

    // Added "Late" column
    String[] columns = {"ID Number", "Date", "Present", "Absent", "Late"};

    public EmpoTable() {
        person = new ArrayList<>();
    }

    public void adding(Employee student) {
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

    public Employee get(int index) {
        return person.get(index);
    }

    public void update(int index, Employee updatedPerson) {
        if (index >= 0 && index < person.size()) {
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
            int monthNumber = getMonthNumber(po.getMonth());
            String formattedDate = String.format("%02d/%02d/%s",
                    monthNumber,
                    Integer.parseInt(po.getDay()),
                    po.getYear());
            return formattedDate;
        } else if (columnIndex == 2) { // Present
            String checkin = po.getCheckin();
            return (checkin != null && !checkin.isEmpty()) ? "Present" : "Absent";
        } else if (columnIndex == 3) { // Absent
            String checkin = po.getCheckin();
            return (checkin == null || checkin.isEmpty()) ? "Absent" : "";
        } else if (columnIndex == 4) { // Late
            String checkin = po.getCheckin();
            // Define your late time cutoff, for example 09:00
            if (checkin == null || checkin.isEmpty()) {
                return "";  // No check-in, so no late status
            }
            return isLate(checkin) ? "Late" : "";
        }

        return null;
    }

    // Helper method to determine if check-in is late
    private boolean isLate(String checkinTime) {
        // Assuming checkinTime format is HH:mm, e.g., "08:45" or "09:15"
        String lateCutoff = "09:00";

        // Simple string compare works if format is HH:mm
        return checkinTime.compareTo(lateCutoff) > 0;
    }

    private int getMonthNumber(String monthName) {
        Map<String, Integer> monthMap = new HashMap<>();
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);

        return monthMap.getOrDefault(monthName, 1); // default to 1 if not found
    }
}
