package org.example;

import javax.swing.*;
import java.awt.*;

public class JavaGui extends JFrame {
    GridBagLayout layout;
    Container container;

    JLabel name, basicSalary, position, deductions, sss, philH,
            pagibig, grosspay, totalDeduction, netpay;
    JTextField namefield, posfield, basicSfield, sssfield, philHfield, pagibigfield,
            grossfield, totaldeducfield, netfield;
    JButton calculate, clear, add, update, delete;

    public JavaGui(String title) {
        this.setTitle(title);

        // BUTTONS
        calculate = new JButton("Calculate Pay");
        clear = new JButton("Clear");
        add = new JButton("Add");
        update = new JButton("Update");
        delete = new JButton("Delete");


        name = new JLabel("Employee Name:");
        position = new JLabel("Position:");
        basicSalary = new JLabel("Basic Salary:");
        deductions = new JLabel("Deductions");
        sss = new JLabel("SSS:");
        philH = new JLabel("PhilHealth:");
        pagibig = new JLabel("Pag-IBIG:");
        grosspay = new JLabel("Gross Pay:");
        totalDeduction = new JLabel("Total Deduction:");
        netpay = new JLabel("Net Pay:");


        namefield = new JTextField(15);
        posfield = new JTextField(15);
        basicSfield = new JTextField(15);
        sssfield = new JTextField(15);
        philHfield = new JTextField(15);
        pagibigfield = new JTextField(15);
        grossfield = new JTextField(15);
        totaldeducfield = new JTextField(15);
        netfield = new JTextField(15);

        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        //Making blue BG
        container.setBackground(new Color(0, 0, 64));


        //Make white text
        name.setForeground(Color.WHITE);
        position.setForeground(Color.WHITE);
        basicSalary.setForeground(Color.WHITE);
        deductions.setForeground(Color.WHITE);
        sss.setForeground(Color.WHITE);
        philH.setForeground(Color.WHITE);
        pagibig.setForeground(Color.WHITE);
        grosspay.setForeground(Color.WHITE);
        totalDeduction.setForeground(Color.WHITE);
        netpay.setForeground(Color.WHITE);

        //fix font
        Font bigFont = new Font("Arial", Font.PLAIN, 16);

        //font big for label
        name.setFont(bigFont);
        position.setFont(bigFont);
        basicSalary.setFont(bigFont);
        deductions.setFont(bigFont);
        sss.setFont(bigFont);
        philH.setFont(bigFont);
        pagibig.setFont(bigFont);
        grosspay.setFont(bigFont);
        totalDeduction.setFont(bigFont);
        netpay.setFont(bigFont);

        //font big for text field
        namefield.setFont(bigFont);
        posfield.setFont(bigFont);
        basicSfield.setFont(bigFont);
        sssfield.setFont(bigFont);
        philHfield.setFont(bigFont);
        pagibigfield.setFont(bigFont);
        grossfield.setFont(bigFont);
        totaldeducfield.setFont(bigFont);
        netfield.setFont(bigFont);

        //font big for button
        calculate.setFont(bigFont);
        clear.setFont(bigFont);
        add.setFont(bigFont);
        update.setFont(bigFont);
        delete.setFont(bigFont);

        //fix collor for buttons
        Color navyLight = new Color(0, 0, 180);
        Color whiteText = Color.WHITE;

        calculate.setBackground(navyLight);
        calculate.setForeground(whiteText);

        clear.setBackground(navyLight);
        clear.setForeground(whiteText);

        add.setBackground(navyLight);
        add.setForeground(whiteText);

        update.setBackground(navyLight);
        update.setForeground(whiteText);

        delete.setBackground(navyLight);
        delete.setForeground(whiteText);

        addtoCon(name, 0, 0, 1, 1);
        addtoCon(namefield, 1, 0, 1, 1);
        addtoCon(position, 0, 1, 1, 1);
        addtoCon(posfield, 1, 1, 1, 1);
        addtoCon(basicSalary, 0, 2, 1, 1);
        addtoCon(basicSfield, 1, 2, 1, 1);
        addtoCon(deductions, 0, 3, 1, 1);
        addtoCon(sss, 0, 4, 1, 1);
        addtoCon(sssfield, 1, 4, 1, 1);
        addtoCon(philH, 0, 5, 1, 1);
        addtoCon(philHfield, 1, 5, 1, 1);
        addtoCon(pagibig, 0, 6, 1, 1);
        addtoCon(pagibigfield, 1, 6, 1, 1);
        addtoCon(calculate, 0, 7, 1, 1);
        addtoCon(clear, 1, 7, 1, 1);
        addtoCon(grosspay, 0, 8, 1, 1);
        addtoCon(grossfield, 1, 8, 1, 1);
        addtoCon(totalDeduction, 0, 9, 1, 1);
        addtoCon(totaldeducfield, 1, 9, 1, 1);
        addtoCon(netpay, 0, 10, 1, 1);
        addtoCon(netfield, 1, 10, 1, 1);
        addtoCon(add, 0, 11, 1, 1);
        addtoCon(update, 1, 11, 1, 1);

        //dpat wla dell
        //addtoCon(delete, 0, 12, 2, 1);
        this.setVisible(true);
        this.pack();

        //stop resize
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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




    //Explain ano ni Commented by: Jayson
    ////////////////////
    public JButton getCalculateButton() {
        return calculate;
    }

    public JButton getClearButton() {
        return clear;
    }

    public JButton getAddButton() {
        return add;
    }

    public JButton getUpdateButton() {
        return update;
    }

    public JButton getDeleteButton() {
        return delete;
    }


    public JTextField getNameField() {
        return namefield;
    }

    public JTextField getPosField() {
        return posfield;
    }

    public JTextField getBasicSField() {
        return basicSfield;
    }

    public JTextField getSssField() {
        return sssfield;
    }

    public JTextField getPhilHField() {
        return philHfield;
    }

    public JTextField getPagibigField() {
        return pagibigfield;
    }

    public JTextField getGrossField() {
        return grossfield;
    }

    public JTextField getTotalDeducField() {
        return totaldeducfield;
    }

    public JTextField getNetField() {
        return netfield;
    }
    ////////////////
}
