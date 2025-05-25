package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaGui extends JFrame {
    GridBagLayout layout;
    Container container;

    private ResultGui2 resultGui2;
    JLabel name, basicSalary, position, deductions, sss, philH,
            pagibig, grosspay, totalDeduction, netpay, tax, paylabel;
    JTextField namefield, posfield, basicSfield, sssfield, philHfield, pagibigfield,
            grossfield, totaldeducfield, netfield, taxfield;
    JButton calculate, clear, add, update, delete;

    JButton BackLog;

    String taxi = "";

    public JavaGui(String title,ResultGui2 resultGui2,String ID, String Month, String Year, String Day, String Checkin, String Checkout,int toalmonthmin,int present,int absent,int late,String name1, String pos) {
        this.setTitle(title);

        this.resultGui2 = resultGui2;
        resultGui2.setVisible(false);

//        ResultGui2 resultGui2 = new ResultGui2("List Table");
//       resultGui2.setVisible(false);

        AttendanceFrame attendanceFrame = new AttendanceFrame("ATTENDANCE LOG",resultGui2,this);
        attendanceFrame.setVisible(false);

        // BUTTONS
        calculate = new JButton("Calculate Pay");
        clear = new JButton("Clear");
        add = new JButton("Add");
        update = new JButton("Update");
        delete = new JButton("Delete");

        BackLog = new JButton("Back");


        name = new JLabel("Employee Name:");
        position = new JLabel("Position:");
        basicSalary = new JLabel("Daily Salary:");
        deductions = new JLabel("Deductions");
        sss = new JLabel("SSS:");
        philH = new JLabel("PhilHealth:");
        pagibig = new JLabel("Pag-IBIG:");
        grosspay = new JLabel("Gross Pay:");
        totalDeduction = new JLabel("Total Deduction:");
        netpay = new JLabel("Net Pay:");
        tax = new JLabel("Philippine Tax:");
        paylabel = new JLabel("Total Pay");


        namefield = new JTextField(15);
        posfield = new JTextField(15);
        basicSfield = new JTextField(15);
        sssfield = new JTextField(15);
        philHfield = new JTextField(15);
        pagibigfield = new JTextField(15);
        grossfield = new JTextField(15);
        totaldeducfield = new JTextField(15);
        netfield = new JTextField(15);
        taxfield = new JTextField(15);

        this.grossfield.setEditable(false);
        this.totaldeducfield.setEditable(false);
        this.netfield.setEditable(false);
        this.sssfield.setEditable(false);
        this.philHfield.setEditable(false);
        this.pagibigfield.setEditable(false);
        this.taxfield.setEditable(false);


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
        tax.setForeground(Color.WHITE);
        paylabel.setForeground(Color.WHITE);

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
        tax.setFont(bigFont);
        paylabel.setFont(bigFont);

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
        taxfield.setFont(bigFont);

        //font big for button
        calculate.setFont(bigFont);
        clear.setFont(bigFont);
        add.setFont(bigFont);
        update.setFont(bigFont);
        delete.setFont(bigFont);
        BackLog.setFont(bigFont);

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

        BackLog.setBackground(navyLight);
        BackLog.setForeground(whiteText);



        addtoCon(name, 0, 0, 1, 1);
        addtoCon(namefield, 1, 0, 1, 1);
        addtoCon(position, 0, 1, 1, 1);
        addtoCon(posfield, 1, 1, 1, 1);
        addtoCon(basicSalary, 0, 2, 1, 1);
        addtoCon(basicSfield, 1, 2, 1, 1);
        addtoCon(calculate, 0, 3, 1, 1);
        addtoCon(clear, 1, 3, 1, 1);

        addtoCon(deductions, 0, 4, 1, 1);
        addtoCon(sss, 0, 5, 1, 1);
        addtoCon(sssfield, 1, 5, 1, 1);
        addtoCon(philH, 0, 6, 1, 1);
        addtoCon(philHfield, 1, 6, 1, 1);
        addtoCon(pagibig, 0, 7, 1, 1);
        addtoCon(pagibigfield, 1, 7, 1, 1);
        addtoCon(tax,0,8,1,1);
        addtoCon(taxfield,1,8,1,1);


        addtoCon(totalDeduction, 0, 9, 1, 1);
        addtoCon(totaldeducfield, 1, 9, 1, 1);
        addtoCon(paylabel,0,10,1,1);
        addtoCon(grosspay, 0, 11, 1, 1);
        addtoCon(grossfield, 1, 11, 1, 1);


        addtoCon(netpay, 0, 12, 1, 1);
        addtoCon(netfield, 1, 12, 1, 1);
        addtoCon(add, 0, 13, 1, 1);
        addtoCon(BackLog, 1, 13, 1, 1);

        //addtoCon(BackLog,0,14,1,1);

        //dpat wla dell
        //addtoCon(delete, 0, 12, 2, 1);
        this.setVisible(true);
        this.pack();

        //stop resize
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);



        final int[] selectedRowIndex = {-1};

        BackLog.addActionListener(e -> {
            this.setVisible(false);
            attendanceFrame.setVisible(true);

        });


        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double salary = Double.parseDouble(basicSfield.getText());
                double totalmins = toalmonthmin;


                double gross = (salary / 480) * totalmins;


                // sss calculations
                double sss = 0;
                if(gross >= 4000 && gross <= 30000){
                    sss = gross * 0.045;
                }else if(gross < 4000){
                    sss = 180;
                }else if(gross > 30000){
                    sss = 1350;
                }
                // philhealth calculations
                double philhealth = 0;
                if(gross >= 10000 && gross <= 100000){
                    philhealth = gross * 0.025;
                }else if(gross < 10000){
                    philhealth = 250;
                }else if(gross > 100000){
                    philhealth = 2500;
                }
                // pagibig calculations
                double pagibig = 0;
                if(gross <= 5000){
                    pagibig = gross * 0.02;
                }else{
                    pagibig = 100;
                }

                // philippine tax calculations
                double tax = 0;
                double totalgross = gross - (sss + pagibig + philhealth);
                System.out.println(totalgross);
                if(totalgross <= 20833){
                    // wala tax kng nubo sa 20,833
                    tax = 0;
                }else if(totalgross >= 20834 && totalgross <= 33332){
                    // 20,834 to 33,332 range
                    // 15% of the excess 20833
                    tax = (totalgross - 20833) * 0.15;
                }else if(totalgross >= 33333 && totalgross <= 66667){
                    // 33,333 to 66,667 range
                    // 20% of the excess 33,333 + 1875
                    tax = ((totalgross - 33333) * 0.2) + 1875;
                }else if(totalgross >= 66668 && totalgross <= 166667){
                    // 66,668 to 166,667 range
                    // 25% of the excess 66,667 + 8,541.80;
                    tax = ((totalgross - 66667) * 0.25) + 8541.80;
                }else if(totalgross >= 166668 && totalgross <= 666667){
                    // 166,668 to 666,667 range
                    // 30% of the excess 166,667 + 33,541.80;
                    tax = ((totalgross - 166667) * 0.3) + 33541.80;
                }else if(totalgross >= 666668){
                    // over 666,668
                    // 35 % of the excess 666,667 + 2 183,541.80;
                    tax = ((totalgross - 666667) * 0.35) + 183541.80;
                }

                double totaldeduction = sss + philhealth + pagibig + tax;
                double netpay = gross - totaldeduction;

                totaldeducfield.setText(String.format("%.2f",totaldeduction));
                taxfield.setText(String.format("%.2f", tax));
                pagibigfield.setText(String.format("%.2f", pagibig));
                philHfield.setText(String.format("%.2f", philhealth));
                sssfield.setText(String.format("%.2f", sss));
                grossfield.setText(String.format("%.2f", gross));
                netfield.setText(String.format("%.2f", netpay));
            }
        });

        getClearButton().addActionListener(e -> {
            if (getNameField().getText().trim().isEmpty() ||
                    getPosField().getText().trim().isEmpty() ||
                    getBasicSField().getText().trim().isEmpty() ||
                    getSssField().getText().trim().isEmpty() ||
                    getPhilHField().getText().trim().isEmpty() ||
                    getPagibigField().getText().trim().isEmpty() ||
                    getGrossField().getText().trim().isEmpty() ||
                    getTotalDeducField().getText().trim().isEmpty() ||
                    getNetField().getText().trim().isEmpty()) {
                // kung indi empty nga miski isa then go to clear
                JOptionPane.showMessageDialog(null, "All fields are already empty!", "Warning", JOptionPane.WARNING_MESSAGE);

                System.out.println(ID);
//                System.out.println(Year);
//                System.out.println(Day);
//                System.out.println(Checkin);
//                System.out.println(Checkout);
//                System.out.println(Month);

            } else {
                // if may isa or tnan empty warning
                namefield.setText("");
                posfield.setText("");
                basicSfield.setText("");
                sssfield.setText("");
                philHfield.setText("");
                pagibigfield.setText("");
                grossfield.setText("");
                totaldeducfield.setText("");
                netfield.setText("");
                taxfield.setText("");
            }
        });


        getAddButton().addActionListener(e -> {
            String fullName = getNameField().getText().trim();
            String[] names = fullName.split(" ", 2);
            String first = names.length > 0 ? names[0] : "";
            String last = names.length > 1 ? names[1] : "";
            String position = getPosField().getText().trim();
            String salary = getBasicSField().getText().trim();
            String sss = getSssField().getText().trim();
            String philHealth = getPhilHField().getText().trim();
            String pagibig = getPagibigField().getText().trim();
            String gross = getGrossField().getText().trim();
            String deduc = getTotalDeducField().getText().trim();
            String net = getNetField().getText().trim();


            if (first.isEmpty() || position.isEmpty() || salary.isEmpty() ||
                    sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty() ||
                    gross.isEmpty() || deduc.isEmpty() || net.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

//            ResultGui2 resultGui2 = new ResultGui2("List Table");
//            resultGui2.setVisible(false);
//            AttendanceFrame attendanceFrame = new AttendanceFrame("ATTENDANCE LOG");
//            attendanceFrame.setVisible(false);

            int maxDays = attendanceFrame.getDaysInCurrentMonth();

            if (attendanceFrame.dayCounter == maxDays) {
                System.out.println(maxDays);
                JOptionPane.showMessageDialog(null, "You must complete 30 days before calculating gross pay.");
                return;
            }


            //no duplication

//            for (int i = 0; i < resultGui2.model.getRowCount(); i++) {
//                Person existing = resultGui2.model.get(i);
//                if (existing.getFirst().equalsIgnoreCase(first) && existing.getLast().equalsIgnoreCase(last)) {
//                    JOptionPane.showMessageDialog(null, "This person is already added.");
//                    return;
//                }
//            }

            for (int i = 0; i < resultGui2.model.getRowCount(); i++) {
                Employee existing = resultGui2.model5.get(i);
                if (existing.getID().equalsIgnoreCase(ID)) {
                    if(existing.getMonth().equalsIgnoreCase(Month) && existing.getYear().equalsIgnoreCase(Year)){
                        JOptionPane.showMessageDialog(null, "This person is already added.");
                        return;
                    }
                }
            }

            String qwe = present + "";
            String asd = absent + "";
            String zxc = late + "";

            System.out.println(qwe + " " + asd + " " + zxc);
            Employee employee = new Employee(ID,Day,Year,Month,qwe,asd,zxc);

            Person person = new Person(first, last, position, salary);
            Deduction deduction = new Deduction(sss, philHealth, pagibig,taxi);
            Total total = new Total(gross, deduc, net);

           // resultGui2.model5.adding(employee);
            resultGui2.model.adding(person);
            resultGui2.model2.adding(deduction);
            resultGui2.model3.adding(total);
            resultGui2.model5.adding(employee);

            //resultGui2.addData(person,deduction,total,employee);
//            System.out.println(employee);

            JOptionPane.showMessageDialog(null, "Person added!");
            getClearButton().doClick();
        });




        update.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = selectedRowIndex[0];
                if (row == -1) {
                    //if wla unod
                    JOptionPane.showMessageDialog(null, "Please select a record first to update.");
                    return;
                }

                // trim means no more space ukson niya ang mga spaces
                String fullName = getNameField().getText().trim();
                String[] names = fullName.split(" ", 2);
                String first = names.length > 0 ? names[0] : "";
                String last = names.length > 1 ? names[1] : "";
                String position = getPosField().getText().trim();
                String salary = getBasicSField().getText().trim();
                String sss = getSssField().getText().trim();
                String philHealth = getPhilHField().getText().trim();
                String pagibig = getPagibigField().getText().trim();
                String gross = getGrossField().getText().trim();
                String deduc = getTotalDeducField().getText().trim();
                String net = getNetField().getText().trim();

                // if wla unod
                if (first.isEmpty() || position.isEmpty() || salary.isEmpty() ||
                        sss.isEmpty() || philHealth.isEmpty() || pagibig.isEmpty() ||
                        gross.isEmpty() || deduc.isEmpty() || net.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                    return;
                }

                // Create updated objects

                String qwe = present + "";
                String asd = absent + "";
                String zxc = late + "";

                System.out.println(qwe + " " + asd + " " + zxc);
                Employee employee = new Employee(ID,Day,Year,Month,qwe,asd,zxc);

                Person updatedPerson = new Person(first, last, position, salary);
                Deduction updatedDeduction = new Deduction(sss, philHealth, pagibig,taxi);
                Total updatedTotal = new Total(gross, deduc, net);

                resultGui2.model5.update(row, employee);
                resultGui2.model.update(row, updatedPerson);
                resultGui2.model2.update(row, updatedDeduction);
                resultGui2.model3.update(row, updatedTotal);

                JOptionPane.showMessageDialog(null, "Record updated!");


                getClearButton().doClick();
                //reset
                selectedRowIndex[0] = -1;
            }
        });
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

}
