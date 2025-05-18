package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendanceFrame extends JFrame {
    GridBagLayout layout;
    Container container;

    JLabel id, totalP, totalA, totalL;
    JTextField idField;
    JButton present, absent, onLeave;

    AttendanceTableModel model;
    JTable table;
    JScrollPane jScrollPane;

    // counters
    int dayCounter = 0;
    int presentCount = 0;
    int absentCount = 0;
    int leaveCount = 0;


    public AttendanceFrame(String title){
        this.setTitle(title);
        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        model = new AttendanceTableModel();
        table = new JTable(model);
        jScrollPane = new JScrollPane(table);
        jScrollPane = createScrollPaneWithStyle(table);



        // LABELS
        id = new JLabel("Identification:");
        totalP = new JLabel("Present Total:");
        totalA = new JLabel("Absent Total:");
        totalL = new JLabel("Total Leaves:");

        // TEXTFIELD
        idField = new JTextField(10);

        // BUTTONS
        present = new JButton("Present");
        absent = new JButton("Absent");
        onLeave = new JButton("OnLeave");

        present.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dayCounter < 30) {
                    dayCounter++;
                    presentCount++;
                    model.addKeys(new AttendanceKey(String.valueOf(dayCounter), "Present"));
                    updateTotals();
                } else {
                    JOptionPane.showMessageDialog(null, "Maximum of 30 days reached.");
                }
            }
        });

        absent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dayCounter < 30) {
                    dayCounter++;
                    absentCount++;
                    model.addKeys(new AttendanceKey(String.valueOf(dayCounter), "Absent"));
                    updateTotals();
                } else {
                    JOptionPane.showMessageDialog(null, "Maximum of 30 days reached.");
                }
            }
        });

        onLeave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dayCounter < 30) {
                    dayCounter++;
                    leaveCount++;
                    model.addKeys(new AttendanceKey(String.valueOf(dayCounter), "On Leave"));
                    updateTotals();
                } else {
                    JOptionPane.showMessageDialog(null, "Maximum of 30 days reached.");
                }
            }
        });

        addtoCon(id,0,0,1,1);
        addtoCon(idField,1,0,1,1);
        addtoCon(present,0,1,1,1);
        addtoCon(absent,0,2,1,1);
        addtoCon(onLeave,0,3,1,1);
        addtoCon(jScrollPane,0,4,2,1);
        addtoCon(totalP,0,5,1,1);
        addtoCon(totalA,0,6,1,1);
        addtoCon(totalL,0,7,1,1);


        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    // resizing
    private JScrollPane createScrollPaneWithStyle(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        return scrollPane;
    }

}
