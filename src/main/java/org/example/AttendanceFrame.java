package org.example;

import javax.swing.*;
import java.awt.*;

public class AttendanceFrame extends JFrame {
    GridBagLayout layout;
    Container container;

    JLabel id, totalP, totalA, totalL;


    JLabel monthLabel;
    JLabel yearLabel;

    JTextField idField;
    JTextField yearField;

    JComboBox<String> monthCombo;
    JButton present, absent, onLeave;

    AttendanceTableModel model;
    JTable table;
    JScrollPane jScrollPane;

    int dayCounter = 0;
    int presentCount = 0;
    int absentCount = 0;
    int leaveCount = 0;

    JPanel buttons;

    JLabel Checkin, Checkout;

    JTextField in, out;

    public AttendanceFrame(String title) {
        this.setTitle(title);
        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        container.setBackground(new Color(0, 0, 64));

        model = new AttendanceTableModel();
        table = new JTable(model);
        jScrollPane = createScrollPaneWithStyle(table);

        // LABELS
        id = new JLabel("Identification Number:");
        totalP = new JLabel("Present Total:");
        totalA = new JLabel("Absent Total:");
        totalL = new JLabel("Total Leaves:");
        monthLabel = new JLabel("Month:");
        yearLabel = new JLabel("Year:");     // new year label
        Checkin = new JLabel("Time of Check in:");
        Checkout = new JLabel("Time of Check out");

        // TEXTFIELDS
        idField = new JTextField(10);
        yearField = new JTextField(5);       // year text field (default width 5)
        yearField.setText("2025");            // default year

        in = new JTextField(10);
        out = new JTextField(10);

        // MONTH COMBOBOX
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        monthCombo = new JComboBox<>(months);

        // BUTTONS
        present = new JButton("Present");
        absent = new JButton("Absent");
        onLeave = new JButton("Late");
        JButton clear = new JButton("Clear");

        buttons = new JPanel();
        buttons.add(present);
        buttons.add(absent);
        buttons.add(onLeave);
        buttons.add(clear);

        clear.addActionListener(e -> {
            idField.setText("");
            dayCounter = 0;
            presentCount = 0;
            absentCount = 0;
            leaveCount = 0;
            model.clearData();
            updateTotals();

            JOptionPane.showMessageDialog(null, "All data has been cleared successfully.", "Cleared", JOptionPane.INFORMATION_MESSAGE);
        });

        present.addActionListener(e -> {
            if (idField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an ID first.", "Missing ID", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int maxDays = getDaysInCurrentMonth();

            if (dayCounter < maxDays) {
                dayCounter++;
                presentCount++;
                model.addKeys(new AttendanceKey(String.valueOf(dayCounter), "Present"));
                updateTotals();
            } else {
                JOptionPane.showMessageDialog(null, "Maximum of " + maxDays + " days reached.");
            }
        });

        absent.addActionListener(e -> {
            if (idField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an ID first.", "Missing ID", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int maxDays = getDaysInCurrentMonth();

            if (dayCounter < maxDays) {
                dayCounter++;
                absentCount++;
                model.addKeys(new AttendanceKey(String.valueOf(dayCounter), "Absent"));
                updateTotals();
            } else {
                JOptionPane.showMessageDialog(null, "Maximum of " + maxDays + " days reached.");
            }
        });

        onLeave.addActionListener(e -> {
            if (idField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an ID first.", "Missing ID", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int maxDays = getDaysInCurrentMonth();

            if (dayCounter < maxDays) {
                dayCounter++;
                leaveCount++;
                model.addKeys(new AttendanceKey(String.valueOf(dayCounter), "On Leave"));
                updateTotals();
            } else {
                JOptionPane.showMessageDialog(null, "Maximum of " + maxDays + " days reached.");
            }
        });

        // Add listener to update day limit when month or year changes
        monthCombo.addActionListener(e -> updateDayLimitOnMonthYearChange());
        yearField.addActionListener(e -> updateDayLimitOnMonthYearChange());

        // Set label colors
        id.setForeground(Color.WHITE);
        totalP.setForeground(Color.WHITE);
        totalA.setForeground(Color.WHITE);
        totalL.setForeground(Color.WHITE);
        monthLabel.setForeground(Color.WHITE);
        yearLabel.setForeground(Color.WHITE);

        // Layout placements
        addtoCon(id, 0, 0, 1, 1);
        addtoCon(idField, 1, 0, 1, 1);

        addtoCon(monthLabel, 0, 1, 1, 1);
        addtoCon(monthCombo, 1, 1, 1, 1);

        addtoCon(yearLabel, 0, 2, 1, 1);
        addtoCon(yearField, 1, 2, 1, 1);

        addtoCon(present, 0, 3, 1, 1);
        addtoCon(absent, 1, 3, 1, 1);
        addtoCon(onLeave, 0, 4, 1, 1);
        addtoCon(clear, 1, 4, 1, 1);

        addtoCon(jScrollPane, 0, 5, 2, 1);
        addtoCon(totalP, 0, 6, 1, 1);
        addtoCon(totalA, 0, 7, 1, 1);
        addtoCon(totalL, 0, 8, 1, 1);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void updateDayLimitOnMonthYearChange() {
        int maxDays = getDaysInCurrentMonth();
        if (dayCounter > maxDays) {
            dayCounter = maxDays;
            JOptionPane.showMessageDialog(null,
                    "Days adjusted to " + maxDays + " due to change in month/year.",
                    "Days Updated", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private int getDaysInCurrentMonth() {
        int month = monthCombo.getSelectedIndex() + 1;
        int year;
        try {
            year = Integer.parseInt(yearField.getText().trim());
        } catch (NumberFormatException e) {
            year = 2025; // default year fallback
            yearField.setText("2025");
        }
        return daysInMonth(month, year);
    }

    public int daysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;
        }
    }

    public boolean isLeapYear(int year) {
        if (year % 4 != 0) return false;
        else if (year % 100 != 0) return true;
        else if (year % 400 != 0) return false;
        else return true;
    }

    public void addtoCon(Component component, int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        container.add(component, gridBagConstraints);
    }

    public void updateTotals() {
        totalP.setText("Present Total: " + presentCount);
        totalA.setText("Absent Total: " + absentCount);
        totalL.setText("Total Leaves: " + leaveCount);
    }

    private JScrollPane createScrollPaneWithStyle(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        return scrollPane;
    }
}
