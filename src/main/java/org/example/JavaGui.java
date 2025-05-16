package org.example;

import javax.swing.*;
import java.awt.*;

public class JavaGui extends JFrame {
    GridBagLayout layout;
    Container container;

    JLabel name, basicSalary, position, deductions,sss,philH,
            pagibig,grosspay,totalDeduction,netpay;
    JTextField namefield,posfield,basicSfield,sssfield,philHfield,pagibigfield,
            grossfield,totaldeducfield,netfield;
    JButton calculate, clear, add,update;


    public JavaGui(String title){
        this.setTitle(title);

        // BUTTONS
        calculate = new JButton("Calculate Pay");
        clear = new JButton("Clear");
        add = new JButton("Add");
        update = new JButton("Update");

        // LABELS
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

        // TEXTFIELDS
        namefield = new JTextField(10);
        posfield = new JTextField(10);
        basicSfield = new JTextField(10);
        sssfield = new JTextField(10);
        philHfield = new JTextField(10);
        pagibigfield = new JTextField(10);
        grossfield = new JTextField(10);
        totaldeducfield = new JTextField(10);
        netfield = new JTextField(10);

        layout = new GridBagLayout();
        container = this.getContentPane();
        container.setLayout(layout);

        addtoCon(name,0,0,1,1);
        addtoCon(namefield,1,0,1,1);
        addtoCon(position,0,1,1,1);
        addtoCon(posfield,1,1,1,1);
        addtoCon(basicSalary,0,2,1,1);
        addtoCon(basicSfield,1,2,1,1);
        addtoCon(deductions,0,3,1,1);
        addtoCon(sss,0,4,1,1);
        addtoCon(sssfield,1,4,1,1);
        addtoCon(philH,0,5,1,1);
        addtoCon(philHfield,1,5,1,1);
        addtoCon(pagibig,0,6,1,1);
        addtoCon(pagibigfield,1,6,1,1);
        addtoCon(calculate,0,7,1,1);
        addtoCon(clear,1,7,1,1);
        addtoCon(grosspay,0,8,1,1);
        addtoCon(grossfield,1,8,1,1);
        addtoCon(totalDeduction,0,9,1,1);
        addtoCon(totaldeducfield,1,9,1,1);
        addtoCon(netpay,0,10,1,1);
        addtoCon(netfield,1,10,1,1);
        addtoCon(add,0,11,1,1);
        addtoCon(update,1,11,1,1);

        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void addtoCon(Component component, int gridx, int gridy, int gridwidth, int gridheight){
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5,5,5,5);

        container.add(component,gridBagConstraints);

    }
}