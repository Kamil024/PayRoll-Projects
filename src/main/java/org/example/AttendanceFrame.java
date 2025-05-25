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
    JButton present, absent, onLeave, clear;

    AttendanceTableModel model;
    JTable table;
    JScrollPane jScrollPane;

    int dayCounter = 0;
    int presentCount = 0;
    int absentCount = 0;
    int lateCount = 0;

    JPanel buttons;

    JLabel Checkin, Checkout;

    JTextField in, out;

    JButton Add, Next;
    ResultGui2 resultGui2;
    JavaGui javaGui;


    int totalMonthlyMinutes = 0;
    public AttendanceFrame(String title,ResultGui2 resultGui2,JavaGui javaGui) {
        this.setTitle(title);
        this.resultGui2 = resultGui2;
        this.javaGui = javaGui;
        resultGui2.setVisible(true);

        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        container.setBackground(new Color(0, 0, 64));

        model = new AttendanceTableModel();
        table = new JTable(model);
        jScrollPane = createScrollPaneWithStyle(table);

        id = new JLabel("Identification Number:");
        totalP = new JLabel("Present Total:");
        totalA = new JLabel("Absent Total:");
        totalL = new JLabel("Total Late/Early:");
        monthLabel = new JLabel("Month:");
        yearLabel = new JLabel("Year:");
        Checkin = new JLabel("Time of Check in:");
        //the attendance or work time should be 8 hours per day
        //fix hour is 8:00 am to 4:00 pm
        //if i in
        Checkout = new JLabel("Time of Check out");

        idField = new JTextField(10);
        yearField = new JTextField(5);
        yearField.setText("2025");

        in = new JTextField("8:00", 10);
        out = new JTextField("16:00", 10);

        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        monthCombo = new JComboBox<>(months);

        // Buttons for attendance marking
        present = new JButton("Add");
        absent = new JButton("Absent");
        onLeave = new JButton("Late");
        clear = new JButton("Clear");

        Add = new JButton("Add");
        Next = new JButton("PayRoll");

        Dimension buttonSize = new Dimension(100, 30);
        present.setPreferredSize(buttonSize);
        absent.setPreferredSize(buttonSize);
        onLeave.setPreferredSize(buttonSize);
        clear.setPreferredSize(buttonSize);
        Add.setPreferredSize(buttonSize);
        Next.setPreferredSize(buttonSize);

        buttons = new JPanel();
        buttons.setBackground(new Color(0, 0, 64));

        // Add all buttons visibly
        buttons.add(present);
        //buttons.add(absent);
        //buttons.add(onLeave);
        buttons.add(clear);
        buttons.add(Next);

        // Set foreground colors for labels
        id.setForeground(Color.WHITE);
        totalP.setForeground(Color.WHITE);
        totalA.setForeground(Color.WHITE);
        totalL.setForeground(Color.WHITE);
        monthLabel.setForeground(Color.WHITE);
        yearLabel.setForeground(Color.WHITE);
        Checkin.setForeground(Color.WHITE);
        Checkout.setForeground(Color.WHITE);

        // Button actions
        present.addActionListener(e -> markAttendance("Present"));
        absent.addActionListener(e -> markAttendance("Absent"));
        onLeave.addActionListener(e -> markAttendance("Late"));

        clear.addActionListener(e -> {
            idField.setText("");
            //in.setText("");
            //out.setText("");
            yearField.setText("2025");  // reset to default year
            dayCounter = 0;
            presentCount = 0;
            absentCount = 0;
            lateCount = 0;
            model.clearData();
            updateTotals();

            JOptionPane.showMessageDialog(null, "All data has been cleared successfully.", "Cleared", JOptionPane.INFORMATION_MESSAGE);
        });

        Next.addActionListener(e -> {
            int maxDays = getDaysInCurrentMonth();

            if (dayCounter < maxDays) {
                JOptionPane.showMessageDialog(null,
                        "You haven't finished counting days. Please complete all attendance entries for the month.",
                        "Incomplete Attendance",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                // Open JavaGui only when all days are counted


                String id = idField.getText();
                String month = (String) monthCombo.getSelectedItem();;
                String day = dayCounter + "";
                String checkin = in.getText();
                String checkout = out.getText();
                String year = yearField.getText();
                JavaGui javaGuiFrame = new JavaGui("Payroll System",resultGui2,id,month,year,day,checkin,checkout,totalMonthlyMinutes,presentCount,absentCount,lateCount);
                javaGuiFrame.setVisible(true);
                this.dispose();  // close the current AttendanceFrame


            }
        });

        monthCombo.addActionListener(e -> updateDayLimitOnMonthYearChange());
        yearField.addActionListener(e -> updateDayLimitOnMonthYearChange());

        // Add components to container using grid bag layout
        addtoCon(id, 0, 0, 1, 1);
        addtoCon(idField, 1, 0, 1, 1);

        addtoCon(monthLabel, 0, 1, 1, 1);
        addtoCon(monthCombo, 1, 1, 1, 1);

        addtoCon(yearLabel, 0, 2, 1, 1);
        addtoCon(yearField, 1, 2, 1, 1);

        addtoCon(Checkin, 0, 3, 1, 1);
        addtoCon(in, 1, 3, 1, 1);

        addtoCon(Checkout, 0, 4, 1, 1);
        addtoCon(out, 1, 4, 1, 1);

        addtoCon(buttons, 0, 5, 2, 1);

        addtoCon(jScrollPane, 0, 6, 2, 1);

        addtoCon(totalP, 0, 7, 1, 1);
        addtoCon(totalA, 0, 8, 1, 1);
        addtoCon(totalL, 0, 9, 1, 1);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    public void markAttendance(String status) {
        if (idField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an ID first.", "Missing ID", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String checkin = in.getText().trim();
        String checkout = out.getText().trim();

        if (checkin.isEmpty() || checkout.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both check-in and check-out times.", "Missing Time", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validate time format (e.g., 0:00 to 23:59)
        if (!isValidTimeFormat(checkin) || !isValidTimeFormat(checkout)) {
            JOptionPane.showMessageDialog(null, "Invalid time format! Please enter time as H:mm or HH:mm (e.g., 8:05, 12:30).", "Invalid Time Format", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // If validation passed, show duration

        int checker = getWorkDurationInMinutes(checkin, checkout);
        //System.out.println(checker);
        if(checker>480){
            return;
        }
        if (checker == 480) {
            status = "Present";
        }
        if (checker < 480 && checker > 0) {
            int totalLateMinutes = 480 - checker;
            status = "Missing " + totalLateMinutes + " minutes";
            lateCount++;
        }
        if (checker == 0) {
            status = "Absent";
        }

        int maxDays = getDaysInCurrentMonth();

        if(dayCounter<maxDays){
            totalMonthlyMinutes += checker;
            System.out.println(totalMonthlyMinutes);
        }

        if (dayCounter < maxDays) {
            dayCounter++;
            switch (status) {
                case "Present":
                    presentCount++;
                    break;
                case "Absent":
                    absentCount++;
                    break;
                case "Late":
                    //this not working due to alot of basta amona
                    lateCount++;
                    break;
            }
            model.addKeys(new AttendanceKey(String.valueOf(dayCounter), status));
            updateTotals();
        } else {
            JOptionPane.showMessageDialog(null, "Maximum of " + maxDays + " days reached.");
        }
        in.setText("8:00");
        out.setText("16:00");
    }

    public boolean isValidTimeFormat(String time) {
        String timePattern = "^([0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        return time.matches(timePattern);
    }


    public int getWorkDurationInMinutes(String checkinTime, String checkoutTime) {
        try {
            String[] inParts = checkinTime.split(":");
            String[] outParts = checkoutTime.split(":");

            int inHours = Integer.parseInt(inParts[0].trim());
            int inMinutes = Integer.parseInt(inParts[1].trim());
            int outHours = Integer.parseInt(outParts[0].trim());
            int outMinutes = Integer.parseInt(outParts[1].trim());

            int checkIn = inHours * 60 + inMinutes;
            int checkOut = outHours * 60 + outMinutes;
            // Fix work
            int workStart = 8 * 60;   // 08:00 = 480 minutes
            int workEnd = 16 * 60;    // 16:00 = 960 minutes
            
            checkIn = Math.max(checkIn, workStart);
            checkOut = Math.min(checkOut, workEnd);
            int duration = checkOut - checkIn;
            if (duration < 0) {
                return 0;
            }
            return duration;
        } catch (Exception e) {
            return -1;
        }
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

    public int getDaysInCurrentMonth() {
        int month = monthCombo.getSelectedIndex() + 1;
        int year;
        try {
            year = Integer.parseInt(yearField.getText().trim());
        } catch (NumberFormatException e) {
            year = 2025; // default fallback
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
        totalL.setText("Total Late/Early: " + lateCount);
    }

    private JScrollPane createScrollPaneWithStyle(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        return scrollPane;
    }
}
