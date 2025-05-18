package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultGui2 extends JFrame {
    GridBagLayout layout;
    Container container;

    Tablee model;
    JTable table;
    JScrollPane jScrollPane;

    TabDeduc model2;
    JTable table2;
    JScrollPane jScrollPane2;

    TabTotal model3;
    JTable table3;
    JScrollPane jScrollPane3;

    TabHours model4;
    JTable table4;
    JScrollPane jScrollPane4;

    JPanel panel;

    JButton addButton;
    JButton deleteButton;

    JPanel TablePanel;

    public ResultGui2(String title){
        this.setTitle(title);
        model = new Tablee();
        table = new JTable(model);
        jScrollPane = createScrollPaneWithStyle(table);

        model2 = new TabDeduc();
        table2 = new JTable(model2);
        jScrollPane2 = createScrollPaneWithStyle(table2);

        model3 = new TabTotal();
        table3 = new JTable(model3);
        jScrollPane3 = createScrollPaneWithStyle(table3);

        model4 = new TabHours();
        table4 = new JTable(model4);
        jScrollPane4 = SmallerTime(table4);

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(0, 0, 64));

        panel.add(addButton);
        panel.add(deleteButton);

        TablePanel = new JPanel();
        TablePanel.add(jScrollPane);
        TablePanel.add(jScrollPane2);
        TablePanel.add(jScrollPane3);

        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);
        container.setBackground(new Color(0, 0, 64));

        addtoCon(TablePanel, 0, 0, 1, 1);
        addtoCon(panel, 0, 1, 1, 1);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Enforce exactly 30 attendance logs before adding to payroll
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int attendanceCount = model4.getRowCount();
                if (attendanceCount < 30) {
                    JOptionPane.showMessageDialog(ResultGui2.this,
                            "You need exactly 30 attendance entries to add to the payroll.\nCurrently: " + attendanceCount,
                            "Not enough entries", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (attendanceCount > 30) {
                    JOptionPane.showMessageDialog(ResultGui2.this,
                            "You have more than 30 attendance entries.\nPlease remove excess records before proceeding.",
                            "Too many entries", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Only proceeds if exactly 30 records
                Person newPerson = new Person("New", "Employee", "Position", "10000");
                model.adding(newPerson);

                JOptionPane.showMessageDialog(ResultGui2.this,
                        "Person added to payroll.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void addtoCon(Container e, int x, int y, int wx, int wy){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = wx;
        gridBagConstraints.gridheight = wy;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5,5,5,5);

        container.add(e, gridBagConstraints);
    }

    private JScrollPane createScrollPaneWithStyle(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(150, 350));
        return scrollPane;
    }

    private JScrollPane SmallerTime(JTable table) {
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(20);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(150, 400));
        return scrollPane;
    }
}
