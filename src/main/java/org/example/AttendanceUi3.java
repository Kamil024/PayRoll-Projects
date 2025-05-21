//package org.example;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class AttendanceUi3 extends JFrame {
//
//
//    //dont mind this prog this is just a test
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    //dont mind this prog this is just a test
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    //dont mind this prog this is just a test
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    //dont mind this prog this is just a test
//
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//
//    GridBagLayout layout;
//    Container container;
//
//    JLabel date, time, inLabel, outLabel, realTimeLabel;
//    JTextField datefield, timefield, inTimeField, outTimeField, realTimeField;
//
//    JLabel ID;
//    JTextField IDfield;
//
//    JButton Present, Absent;
//
//    private Timer sessionTimer;
//    private Timer liveClockTimer;
//    private long startTime;
//
//    private final SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss a");
//    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
//    private final SimpleDateFormat fullFormatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
//
//    public AttendanceUi3(String title) {
//        this.setTitle(title);
//        layout = new GridBagLayout();
//        container = this.getContentPane();
//        container.setLayout(layout);
//        container.setBackground(new Color(0, 0, 64));
//
//        // Labels and Fields
//        date = new JLabel("Date:");
//        datefield = new JTextField(10);
//        datefield.setEditable(false);
//        datefield.setText(dateFormatter.format(new Date())); // current date
//
//        realTimeLabel = new JLabel("Now:");
//        realTimeField = new JTextField(15);
//        realTimeField.setEditable(false);
//
//        time = new JLabel("Elapsed:");
//        timefield = new JTextField(10);
//        timefield.setEditable(false);
//
//        ID = new JLabel("ID:");
//        IDfield = new JTextField(10);
//
//        inLabel = new JLabel("Check-in:");
//        inTimeField = new JTextField(15);
//        inTimeField.setEditable(false);
//
//        outLabel = new JLabel("Check-out:");
//        outTimeField = new JTextField(15);
//        outTimeField.setEditable(false);
//
//        Present = new JButton("Check in");
//        Absent = new JButton("Check out");
//
//        // Fonts and colors
//        Font bigFont = new Font("Arial", Font.PLAIN, 16);
//        Color whiteText = Color.WHITE;
//        Color navyLight = new Color(0, 0, 180);
//
//        for (JComponent comp : new JComponent[]{date, time, ID, inLabel, outLabel, realTimeLabel,
//                datefield, timefield, IDfield, inTimeField, outTimeField, realTimeField,
//                Present, Absent}) {
//            comp.setFont(bigFont);
//        }
//
//        date.setForeground(whiteText);
//        time.setForeground(whiteText);
//        ID.setForeground(whiteText);
//        inLabel.setForeground(whiteText);
//        outLabel.setForeground(whiteText);
//        realTimeLabel.setForeground(whiteText);
//
//        Present.setBackground(navyLight);
//        Present.setForeground(whiteText);
//        Absent.setBackground(navyLight);
//        Absent.setForeground(whiteText);
//
//        // Layout
//        addtoCon(ID, 0, 0, 1, 1);
//        addtoCon(IDfield, 1, 0, 1, 1);
//        addtoCon(date, 0, 1, 1, 1);
//        addtoCon(datefield, 1, 1, 1, 1);
//        addtoCon(realTimeLabel, 0, 2, 1, 1);
//        addtoCon(realTimeField, 1, 2, 1, 1);
//        addtoCon(inLabel, 0, 3, 1, 1);
//        addtoCon(inTimeField, 1, 3, 1, 1);
//        addtoCon(outLabel, 0, 4, 1, 1);
//        addtoCon(outTimeField, 1, 4, 1, 1);
//        addtoCon(time, 0, 5, 1, 1);
//        addtoCon(timefield, 1, 5, 1, 1);
//        addtoCon(Present, 0, 6, 1, 1);
//        addtoCon(Absent, 1, 6, 1, 1);
//
//        // Session Timer
//        sessionTimer = new Timer(1000, (ActionEvent e) -> {
//            long elapsedMillis = System.currentTimeMillis() - startTime;
//            long seconds = (elapsedMillis / 1000) % 60;
//            long minutes = (elapsedMillis / (1000 * 60)) % 60;
//            long hours = (elapsedMillis / (1000 * 60 * 60));
//
//            String timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);
//            timefield.setText(timeFormatted);
//        });
//
//        // Live Clock Timer
//        liveClockTimer = new Timer(1000, e -> {
//            realTimeField.setText(fullFormatter.format(new Date()));
//        });
//        liveClockTimer.start();
//
//        // Actions
//        Present.addActionListener(e -> {
//
//            //if wla id will not check in
//            String idText = IDfield.getText().trim();
//            if (idText.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "Please enter your ID before checking in.", "Warning", JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//
//
//            startTime = System.currentTimeMillis();
//            sessionTimer.start();
//
//            String now = fullFormatter.format(new Date());
//            inTimeField.setText(now);
//            outTimeField.setText("");
//            timefield.setText("00:00:00");
//
//            JavaGui javaGui = new JavaGui("Payroll System");
//            ResultGui2 resultGui2 = new ResultGui2("List Table");
//
//
//            Present.setEnabled(false);
//            Absent.setEnabled(true);
//        });
//
//        Absent.addActionListener(e -> {
//            sessionTimer.stop();
//            String out = fullFormatter.format(new Date());
//            outTimeField.setText(out);
//
//            Absent.setEnabled(false);
//            Present.setEnabled(true);
//
//            JOptionPane.showMessageDialog(this,
//                    "Checked out!\nIn: " + inTimeField.getText() + "\nOut: " + out +
//                            "\nTotal: " + timefield.getText(),
//                    "Attendance Summary",
//                    JOptionPane.INFORMATION_MESSAGE);
//        });
//
//        Absent.setEnabled(false); // disable at first
//
//        this.setSize(500, 400);
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setVisible(true);
//    }
//
//    public void addtoCon(Component component, int gridx, int gridy, int gridwidth, int gridheight) {
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = gridx;
//        gbc.gridy = gridy;
//        gbc.gridwidth = gridwidth;
//        gbc.gridheight = gridheight;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.insets = new Insets(10, 10, 5, 10);
//        container.add(component, gbc);
//    }
//}
